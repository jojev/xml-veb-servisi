package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.exception.BadLogicException;


import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.Doza;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import main.java.com.xml.userbackend.transformations.HtmlTransformer;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SaglasnostService implements ISaglasnostService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private InteresovanjeService interesovanjeService;

    private HtmlTransformer htmlTransformer;
    
    private XSLFOTransformer xslfoTransformer;

    private JaxBParser jaxBParser;

    public SaglasnostService(ExistDbManager existDbManager,BaseRepository baseRepository, HtmlTransformer htmlTransformer, 
                             MetadataExtractor metadataExtractor, InteresovanjeService interesovanjeService,

                             JaxBParser jaxBParser, XSLFOTransformer xslfoTransformer){
                          
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.htmlTransformer = htmlTransformer;
        this.interesovanjeService = interesovanjeService;
        this.jaxBParser =  jaxBParser;
        this.xslfoTransformer = xslfoTransformer;
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
        entity.setAbout("http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije/");
        entity.setAbout(entity.getAbout() + userId);
        entity.setTypeof("pred:IdentifikatorDokumenta");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        entity.getPodaciKojeJePopunioPacijent().getLicniPodaci().getJmbg().setDatatype("xs:string");
        entity.getPodaciKojeJePopunioPacijent().getZeljenaVakcina().setDatatype("xs:string");
        entity.getPodaciKojeJePopunioPacijent().getDatum().setDatatype("xs:string");
        entity.getPodaciKojeJePopunioPacijent().getLicniPodaci().getJmbg().setProperty("pred:Kreirao");
        entity.getPodaciKojeJePopunioPacijent().getZeljenaVakcina().setProperty("pred:ZeljenaVakcina");
        entity.getPodaciKojeJePopunioPacijent().getDatum().setProperty("pred:KreiranDatuma");
        if (entity.getPodaciKojeJePopunioZdravstveniRadnik() != null) {
            for (int i = 0; i < entity.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().size(); i++) {
                entity.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().get(i).getDatumDavanjaVakcine().setDatatype("xs:date");
                entity.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().get(i).getDatumDavanjaVakcine().setProperty("pred:VakcinisanDatuma");
            }
        }
        RDFNode rdfNode = interesovanjeService.getInteresovanje(entity.getPodaciKojeJePopunioPacijent().getLicniPodaci().getJmbg().getValue());

        if(rdfNode == null) {
            throw new BadLogicException("Prvo morate podneti interesovanje.");
        }

        entity.setInteresovanjeRef(new ObrazacZaSprovodjenjeImunizacije.InteresovanjeRef());
        entity.getInteresovanjeRef().setProperty("pred:Referencira");
        entity.getInteresovanjeRef().setDatatype("xs:string");
        String[] documentUri = rdfNode.toString().split("/");
        entity.getInteresovanjeRef().setValue(documentUri[documentUri.length - 1]);

        baseRepository.save("/db/saglasnost", userId, entity, ObrazacZaSprovodjenjeImunizacije.class);
        XMLResource resource = existDbManager.load("/db/saglasnost", userId);
        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
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
    public String readMetadata(String documentId, String format) throws IOException {
        String sparqlCondition = "<http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije/" + documentId + "> ?d ?s .";
        try {
            return FusekiReader.readMetadata("/saglasnosti", sparqlCondition, format);
        }
        catch (Exception e) {
            throw new MissingEntityException("Ne postoji saglasnost sa tim id.");
        }
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                                   PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception {
        RDFNode saglasnostID = this.getSaglasnostIdFromJMBG(jmbg);
        List<RDFNode> allSaglasnost = this.getAllSaglanostFromJMBG(jmbg);
        if (saglasnostID == null) {
            throw new MissingEntityException("Ne postoji saglasnost za unijetog korisnika.");
        }
        String[] parts = saglasnostID.toString().split("/");
        ObrazacZaSprovodjenjeImunizacije obrazacZaSprovodjenjeImunizacije;
        System.out.println(allSaglasnost.size());
        podaci.getDoze().getDoza().get(0).getDatumDavanjaVakcine().setProperty("pred:VakcinisanDatuma");
        podaci.getDoze().getDoza().get(0).getDatumDavanjaVakcine().setDatatype("xs:date");
        if (allSaglasnost.size() > 1) {
            String[] parts1 = allSaglasnost.get(allSaglasnost.size() - 2).toString().split("/");
            System.out.println(parts1[parts1.length-1]);
            ObrazacZaSprovodjenjeImunizacije pretposljednjiObrazacZaSprovodjenjeImunizacije =
                    baseRepository.findById("/db/saglasnost", parts1[parts1.length - 1], ObrazacZaSprovodjenjeImunizacije.class);
            XMLResource resource = existDbManager.load("/db/saglasnost", parts1[parts1.length - 1]);
            System.out.println(resource);
            Doza secondDoza = podaci.getDoze().getDoza().get(podaci.getDoze().getDoza().size()-1);
            podaci.getDoze().getDoza().set(0, pretposljednjiObrazacZaSprovodjenjeImunizacije.getPodaciKojeJePopunioZdravstveniRadnik().getDoze().getDoza().get(0));
            podaci.getDoze().getDoza().add(secondDoza);
        }
        String content = jaxBParser.marshallWithoutSchema(podaci).toString();
        content = content.replace(" xmlns=\"http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije\"", "");
        content = content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        baseRepository.insertAfter("/db/saglasnost", parts[parts.length - 1],
                "/obrazac_za_sprovodjenje_imunizacije/podaci_koje_je_popunio_pacijent", content,
                "http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije");
        XMLResource resource = existDbManager.load("/db/saglasnost", parts[parts.length - 1]);
        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "saglasnosti");
        obrazacZaSprovodjenjeImunizacije =
                baseRepository.findById("/db/saglasnost", parts[parts.length - 1], ObrazacZaSprovodjenjeImunizacije.class);
        return obrazacZaSprovodjenjeImunizacije;
    }

    @Override
    public RDFNode getSaglasnostIdFromJMBG(String jmbg) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao> \"" + jmbg + "\" .";
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                if (!result.getResult().hasNext()) {
                    String columnName = columnNames.get(0);
                    RDFNode rdfNode = row.get(columnName);
                    return rdfNode;
                }
            }
            return null;
        }
    }
    
    public ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException {
        String jmbg = searchDTO.getSearch();

        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao> \"" + jmbg + "\".";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }

    @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByText(SearchDTO searchDTO) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String xqueryPath = "data/xquery/pretraga_po_tekstu_obrazac.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, searchDTO.getSearch());
        System.out.println(formattedXQueryExpresion);
        List<Resource> resources =
                existDbManager.executeXquery("/db/saglasnost", "http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije", formattedXQueryExpresion);
        ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazacZaSprovodjenjeImunizacijes = new ArrayList<ObrazacZaSprovodjenjeImunizacije>();
        for (Resource resource : resources) {
            XMLResource xmlResource = (XMLResource) resource;
            obrazacZaSprovodjenjeImunizacijes.add((ObrazacZaSprovodjenjeImunizacije) jaxBParser.unmarshall(xmlResource, ObrazacZaSprovodjenjeImunizacije.class));
        }
        return obrazacZaSprovodjenjeImunizacijes;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
       
//    @Override
//    public ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(String jmbg) throws Exception {
//        ArrayList<RDFNode> nodes = (ArrayList<RDFNode>) getAllSaglanostFromJMBG(jmbg);
//        ArrayList<ObrazacZaSprovodjenjeImunizacije> list = new ArrayList<>();
//        for (RDFNode node : nodes) {
//            String[] parts = node.toString().split("/");
//            ObrazacZaSprovodjenjeImunizacije obrazac = findById(parts[parts.length - 1]);
//            list.add(obrazac);
//        }
//
//        return list;
//    }


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

    @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(SearchDTO searchDTO) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(searchDTO);
        ArrayList<ObrazacZaSprovodjenjeImunizacije> list = new ArrayList<>();
        for(RDFNode node: nodes){
            String[] parts = node.toString().split("/");
            ObrazacZaSprovodjenjeImunizacije obrazac = baseRepository.findById("/db/saglasnost",
                    parts[parts.length - 1], ObrazacZaSprovodjenjeImunizacije.class);
            list.add(obrazac);
        }

        return list;
    }

    @Override
    public byte[] generateSaglasnostToXHTML(String id) throws Exception {
    	ObrazacZaSprovodjenjeImunizacije saglasnost = findById(id);
    	return htmlTransformer.generateHTMLtoByteArray(saglasnost);
    }
        
    @Override
    public byte[] generateSaglasnostToPDF(String id) throws Exception {
    	ObrazacZaSprovodjenjeImunizacije saglasnost = findById(id);
    	return xslfoTransformer.generatePDFtoByteArray(saglasnost);
    }
        


    public List<RDFNode> getAllSaglanostFromJMBG(String jmbg) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/saglasnosti/predicate/Kreirao> \"" + jmbg + "\" .";
        List<RDFNode> nodes = new ArrayList<>();

        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                nodes.add(rdfNode);

            }
            return nodes;
        }
    }

	@Override
	public String getByDopunjenDatuma(XMLGregorianCalendar calendar) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
