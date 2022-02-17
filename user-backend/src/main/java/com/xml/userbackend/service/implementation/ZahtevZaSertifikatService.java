package main.java.com.xml.userbackend.service.implementation;


import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;

import main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinacijiList;

import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.EmailService;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;

import main.java.com.xml.userbackend.transformations.HtmlTransformer;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;


import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ZahtevZaSertifikatService implements IZahtevZaSertifikatService {
    private BaseRepository baseRepository;


    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;
    
    private HtmlTransformer htmlTransformer;

    private JaxBParser jaxBParser;
    
    private EmailService emailService;
    
    private IInteresovanjeService interesovanjeService;

    private RestTemplate restTemplate;

    @Autowired
    public ZahtevZaSertifikatService(BaseRepository baseRepository, JaxBParser jaxBParser, HtmlTransformer htmlTransformer,
                                     ExistDbManager existDbManager, MetadataExtractor metadataExtractor,
                                     EmailService emailService, IInteresovanjeService interesovanjeService, RestTemplate restTemplate) {



        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.htmlTransformer = htmlTransformer;
        this.emailService = emailService;
        this.interesovanjeService = interesovanjeService;
        this.jaxBParser = jaxBParser;
        this.restTemplate = restTemplate;


    }

    @Override
    public List<ZahtevZaIzdavanjeSertifikata> findAll() {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata findById(String id) throws Exception {
        return baseRepository.findById("/db/zahtev_za_sertifikat", id, ZahtevZaIzdavanjeSertifikata.class);
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata entity) throws Exception {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata update(ZahtevZaIzdavanjeSertifikata entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public String readMetadata(String documentId, String format) throws IOException {
        String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/" + documentId + "> ?d ?s .";
        try {
            return FusekiReader.readMetadata("/zahtev_za_sertifikat", sparqlCondition, format);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MissingEntityException("Ne postoji zahtev za sertifikat sa tim id.");
        }
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata, String accessToken) throws Exception {
        String documentId = UUID.randomUUID().toString();
        zahtevZaIzdavanjeSertifikata.setAbout("http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/" + documentId);
        zahtevZaIzdavanjeSertifikata.setTypeof("pred:IdentifikatorDokumenta");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getJmbg().setProperty("pred:PodneoJmbg");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getJmbg().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getBrojPasosa().setProperty("pred:PodneoBrPasosa");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getBrojPasosa().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getDatumPodnosenjaZahteva().setProperty("pred:IzdatDatuma");
        zahtevZaIzdavanjeSertifikata.getDatumPodnosenjaZahteva().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/");
        zahtevZaIzdavanjeSertifikata.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        baseRepository.save("db/zahtev_za_sertifikat", documentId, zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);

        XMLResource resource = existDbManager.load("/db/zahtev_za_sertifikat", documentId);

        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "zahtev_za_sertifikat");
        return zahtevZaIzdavanjeSertifikata;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata setOdgovor(RazlogDTO razlogDTO) throws Exception {
        String odgovor = "odobren";
        if (!razlogDTO.getOdobren()) {
            odgovor = "odbijen";
        }

        baseRepository.update("/db/zahtev_za_sertifikat", razlogDTO.getZahtev(), "/zahtev_za_izdavanje_sertifikata/odgovor", odgovor,
                "http://www.ftn.uns.ac.rs/zahtev_za_sertifikat");

        return findById(razlogDTO.getZahtev());

    }

    @Override
    public int getNumberOfRequestForDigitalSertificate(String startDate, String endDate) throws IOException {
        String sparqlCondition = "?s <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/IzdatDatuma> ?date. "
                + "FILTER ( ?date >= \"" + startDate + "\"^^<http://www.w3.org/2001/XMLSchema#date> && ?date < \"" + endDate + "\"^^<http://www.w3.org/2001/XMLSchema#date>).";

        try (RDFReadResult result = FusekiReader.readRDFWithSparqlCountQuery("/zahtev_za_sertifikat", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();

            if (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                return rdfNode.asLiteral().getInt();
            }
        }
        return 0;
    }

    @Override
    public ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException {
        String jmbg = searchDTO.getSearch();
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoJmbg> \"" + jmbg + "\" ;";

        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition)) {
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
    public ArrayList<ZahtevZaIzdavanjeSertifikata> searchByJMBG(SearchDTO searchDTO) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(searchDTO);
        ArrayList<ZahtevZaIzdavanjeSertifikata> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = findById(parts[parts.length - 1]);
            list.add(zahtevZaIzdavanjeSertifikata);
        }
        return list;
    }

    @Override
    public ArrayList<ZahtevZaIzdavanjeSertifikata> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception {
        String value = metadataSearchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<ZahtevZaIzdavanjeSertifikata> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = findById(parts[parts.length - 1]);
            list.add(zahtevZaIzdavanjeSertifikata);
        }
        return list;
    }

    
    @Override
    public byte[] generateZahtevToXHTML(String id) throws Exception {
    	ZahtevZaIzdavanjeSertifikata zahtev = findById(id);
    	return htmlTransformer.generateHTMLtoByteArray(zahtev);
    }


    @Override
    public ArrayList<ZahtevZaIzdavanjeSertifikata> searchByText(String search) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String xqueryPath = "data/xquery/pretraga_po_tekstu_zahtev.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, search);
        System.out.println(formattedXQueryExpresion);
        List<Resource> resources =
                existDbManager.executeXquery("/db/zahtev_za_sertifikat", "http://www.ftn.uns.ac.rs/zahtev_za_sertifikat", formattedXQueryExpresion);
        ArrayList<ZahtevZaIzdavanjeSertifikata> interesovanjeZaVakcinisanjes = new ArrayList<ZahtevZaIzdavanjeSertifikata>();
        for (Resource resource : resources) {
            XMLResource xmlResource = (XMLResource) resource;
            interesovanjeZaVakcinisanjes.add((ZahtevZaIzdavanjeSertifikata) jaxBParser.unmarshall(xmlResource, ZahtevZaIzdavanjeSertifikata.class));
        }
        return interesovanjeZaVakcinisanjes;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    @Override
    public ArrayList<ZahtevZaIzdavanjeSertifikata> findPendingZahtevi() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String xqueryPath = "data/xquery/neodobreni_zahtevi.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, "ceka odgovor");
        List<Resource> resources =
                existDbManager.executeXquery("/db/zahtev_za_sertifikat", "http://www.ftn.uns.ac.rs/zahtev_za_sertifikat", formattedXQueryExpresion);
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = new ArrayList<>();
        for (Resource resource : resources) {
            XMLResource xmlResource = (XMLResource) resource;
            zahtevi.add((ZahtevZaIzdavanjeSertifikata) jaxBParser.unmarshall(xmlResource, ZahtevZaIzdavanjeSertifikata.class));
        }
        return zahtevi;
    }

    public String getByJmbg(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoJmbg> \"" + jmbg + "\" ;";

        String lastZahtev = "";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            if (!result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                lastZahtev = row.get(columnName).toString();
            }

        }
        String[] documentUri = lastZahtev.split("/");
        return documentUri[documentUri.length - 1];
    }

}
