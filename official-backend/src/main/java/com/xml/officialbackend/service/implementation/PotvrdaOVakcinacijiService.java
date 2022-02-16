package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IListaCekanjaService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.checkerframework.checker.units.qual.A;
import main.java.com.xml.officialbackend.service.contract.ITerminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@Service
public class PotvrdaOVakcinacijiService implements IPotvrdaOVakcinacijiService {
    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private MetadataExtractor metadataExtractor;

    @Autowired
    private ITerminService terminService;

    @Autowired
    private JaxBParser jaxBParser;

    @Autowired
    private IListaCekanjaService listaCekanjaService;

    @Override
    public List<PotvrdaOVakcinaciji> findAll() {
        return null;
    }

    @Override
    public PotvrdaOVakcinaciji findById(String id) throws Exception {
        return null;
    }

    @Override
    public PotvrdaOVakcinaciji create(PotvrdaOVakcinaciji entity) throws Exception {
        String potvrdaOVakcinicijiId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/" + potvrdaOVakcinicijiId);
        entity.setTypeof("pred:IdentifikatorDokumenta");
        entity.getLicniPodaci().getJmbg().setProperty("pred:KreiranZa");
        entity.getLicniPodaci().getJmbg().setDatatype("xs:string");
        entity.getDatumIzdavanjaPotvrde().setProperty("pred:Izdat");
        entity.getDatumIzdavanjaPotvrde().setDatatype("xs:date");
        entity.getQrKod().setProperty("pred:Linkuje");
        entity.getQrKod().setDatatype("xs:string");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        baseRepository.save("/db/potvrda_o_vakcinaciji", potvrdaOVakcinicijiId, entity, PotvrdaOVakcinaciji.class);

        XMLResource resource = existDbManager.load("/db/potvrda_o_vakcinaciji", potvrdaOVakcinicijiId);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "potvrda_o_vakcinaciji");

        int numberOfVaccine = entity.getPodaciOVakcinaciji().getDoze().getDoza().size();

        terminService.addTerminOrAddToListaCekanja(entity.getPodaciOVakcinaciji().getNazivVakcine().trim(), numberOfVaccine + 1,
                entity.getLicniPodaci().getJmbg().getValue(), entity.getPodaciOVakcinaciji().getDoze().getDoza().get(numberOfVaccine - 1).getDatumDavanja());

        return entity;
    }

    @Override
    public PotvrdaOVakcinaciji update(PotvrdaOVakcinaciji entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    
    public int getNumberOfVaccinated(LocalDate startDate, LocalDate endDate) throws IOException {
    	String sparqlCondition = "?s <http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/Izdat> ?date. "
				+ "FILTER ( ?date >= \"" + startDate + "\"^^<http://www.w3.org/2001/XMLSchema#date> && ?date < \"" + endDate + "\"^^<http://www.w3.org/2001/XMLSchema#date>)." ;

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlCountQuery("/potvrdaOVakcinaciji", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            
            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                System.out.println(rdfNode.asLiteral().getInt());
                return rdfNode.asLiteral().getInt();
            }
        }
		return 0;
    }
    
    public ArrayList<RDFNode> searchRDF(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/KreiranZa> \"" + jmbg + "\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral> ;";

        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/potvrda_o_vakcinaciji", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));

            }
            return nodes;
        }
    }
    @Override
    public ArrayList<PotvrdaOVakcinaciji> findPotvrdeByJMBG(String jmbg) throws Exception {
        ArrayList<PotvrdaOVakcinaciji> potvrde = new ArrayList<>();
        ArrayList<RDFNode> nodes = searchRDF(jmbg);
        for(RDFNode node: nodes){
            String[] parts = node.toString().split("/");
            PotvrdaOVakcinaciji potvrda = baseRepository.findById("/db/potvrda_o_vakcinaciji",
                    parts[parts.length - 1], PotvrdaOVakcinaciji.class);
            potvrde.add(potvrda);
        }
        return potvrde;
    }


    @Override
    public ArrayList<PotvrdaOVakcinaciji> searchByText(SearchDTO searchDTO) throws XMLDBException, SAXException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, JAXBException {
        String xqueryPath = "data/xquery/pretraga_po_tekstu_potvrda.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, searchDTO.getSearch());
        System.out.println(formattedXQueryExpresion);
        List<Resource> resources =
                existDbManager.executeXquery("/db/potvrda_o_vakcinaciji", "http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji",formattedXQueryExpresion);
        ArrayList<PotvrdaOVakcinaciji> potvrdaOVakcinacijis =  new ArrayList<PotvrdaOVakcinaciji>();
        for(Resource resource:resources){
            XMLResource xmlResource  = (XMLResource) resource;
            potvrdaOVakcinacijis.add((PotvrdaOVakcinaciji) jaxBParser.unmarshall(xmlResource,PotvrdaOVakcinaciji.class));
        }
        return  potvrdaOVakcinacijis;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
