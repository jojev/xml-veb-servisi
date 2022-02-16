package main.java.com.xml.userbackend.service.implementation;


import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.Doza;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SaglasnostService implements ISaglasnostService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private InteresovanjeService interesovanjeService;

    private JaxBParser jaxBParser;

    public SaglasnostService(ExistDbManager existDbManager,BaseRepository baseRepository,
                             MetadataExtractor metadataExtractor, InteresovanjeService interesovanjeService,
                             JaxBParser jaxBParser){
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.interesovanjeService = interesovanjeService;
        this.jaxBParser =  jaxBParser;
    }
    @Override
    public List<ObrazacZaSprovodjenjeImunizacije> findAll() {
        return null;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije findById(String id) throws Exception {
        return baseRepository.findById("/db/saglasnost", id, ObrazacZaSprovodjenjeImunizacije.class);
    }


    @Override
    public ObrazacZaSprovodjenjeImunizacije create(ObrazacZaSprovodjenjeImunizacije entity) throws Exception {
        String userId = UUID.randomUUID().toString();
        entity.setAbout(entity.getAbout()+userId);
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        entity.getPodaciKojeJePopunioPacijent().getLicniPodaci().getJmbg().setDatatype("xs:string");
        entity.getPodaciKojeJePopunioPacijent().getZeljenaVakcina().setDatatype("xs:string");
        entity.getPodaciKojeJePopunioPacijent().getDatum().setDatatype("xs:string");
        if(entity.getPodaciKojeJePopunioZdravstveniRadnik()!=null) {
            for (int i = 0; i < entity.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().size(); i++) {
                entity.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().get(i).getDatumDavanjaVakcine().setDatatype("xs:date");
            }
        }
        RDFNode rdfNode = interesovanjeService.getInteresovanje(entity.getPodaciKojeJePopunioPacijent().getLicniPodaci().getJmbg().getValue());
        entity.setReferencira(rdfNode.toString());
        baseRepository.save("/db/saglasnost", userId, entity, ObrazacZaSprovodjenjeImunizacije.class);
        XMLResource resource = existDbManager.load("/db/saglasnost", userId);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "saglasnosti");
        return entity;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(ObrazacZaSprovodjenjeImunizacije entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                                   PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception {
        RDFNode saglasnostID = this.getSaglasnostIdFromJMBG(jmbg);
        if(saglasnostID==null){
            throw new MissingEntityException("Ne postoji saglasnost za unijetog korisnika.");
        }
        String[] parts = saglasnostID.toString().split("/");
        ObrazacZaSprovodjenjeImunizacije obrazacZaSprovodjenjeImunizacije =
                baseRepository.findById("/db/saglasnost", parts[parts.length-1],ObrazacZaSprovodjenjeImunizacije.class);
        if(obrazacZaSprovodjenjeImunizacije.getPodaciKojeJePopunioZdravstveniRadnik()!=null){
            Doza secondDoza = podaci.getDoze().getDoza().get(0);
            podaci.getDoze().getDoza().set(0,obrazacZaSprovodjenjeImunizacije.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().get(0));
            podaci.getDoze().getDoza().add(secondDoza);
            baseRepository.removeElement("/db/saglasnost", parts[parts.length-1],
                    "/obrazac_za_sprovodjenje_imunizacije/podaci_koje_je_popunio_zdravstveni_radnik",
                    "http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije");
        }
        String content = jaxBParser.marshallWithoutSchema(podaci).toString();
        System.out.println(content);
        content =  content.replace(" xmlns=\"http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije\"", "");
        content =  content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

        baseRepository.insertAfter("/db/saglasnost", parts[parts.length-1],
                "/obrazac_za_sprovodjenje_imunizacije/podaci_koje_je_popunio_pacijent", content,
                "http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije");

        obrazacZaSprovodjenjeImunizacije =
                baseRepository.findById("/db/saglasnost", parts[parts.length-1],ObrazacZaSprovodjenjeImunizacije.class);

        return obrazacZaSprovodjenjeImunizacije;
    }


    public RDFNode getSaglasnostIdFromJMBG(String jmbg) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao> \"" + jmbg + "\" .";
        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                if(!result.getResult().hasNext()) {
                    String columnName = columnNames.get(0);
                    RDFNode rdfNode = row.get(columnName);
                    return rdfNode;
                }
            }
            return null;
        }
    }

    public ArrayList<RDFNode> searchRDF(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao> \"" + jmbg + "\" ;";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            if (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }


    @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(String jmbg) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(jmbg);
        ArrayList<ObrazacZaSprovodjenjeImunizacije> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ObrazacZaSprovodjenjeImunizacije obrazac = findById(parts[parts.length - 1]);
            list.add(obrazac);
        }

        return list;
    }



    @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception {
        String value = metadataSearchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\"";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<ObrazacZaSprovodjenjeImunizacije> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ObrazacZaSprovodjenjeImunizacije obrazacZaSprovodjenjeImunizacije = findById(parts[parts.length - 1]);
            list.add(obrazacZaSprovodjenjeImunizacije);
        }
        return list;
    }

}
