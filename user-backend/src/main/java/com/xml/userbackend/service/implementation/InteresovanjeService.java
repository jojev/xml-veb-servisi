package main.java.com.xml.userbackend.service.implementation;


import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.EmailService;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.transformations.HtmlTransformer;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.base.Resource;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
public class InteresovanjeService implements IInteresovanjeService {

    private BaseRepository baseRepository;

    private EmailService emailService;

    private XSLFOTransformer xslfoTransformer;
    
    private HtmlTransformer htmlTransformer;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;


    @Autowired
    public InteresovanjeService(BaseRepository baseRepository, EmailService emailService, XSLFOTransformer xslfoTransformer, HtmlTransformer htmlTransformer,
                                JaxBParser jaxBParser, ExistDbManager existDbManager, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.emailService = emailService;
        this.htmlTransformer = htmlTransformer;
        this.xslfoTransformer = xslfoTransformer;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;

    }

    @Override
    public List<InteresovanjeZaVakcinisanje> findAll() {
        return null;
    }

    @Override
    public InteresovanjeZaVakcinisanje findById(String id) throws Exception {
        return baseRepository.findById("/db/interesovanje", id, InteresovanjeZaVakcinisanje.class);
    }

    @Override
    public RDFNode getInteresovanje(String jmbg) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Kreirao> \"" + jmbg + "\" .";

        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/interesovanje", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            System.out.println(columnNames.get(0));
            System.out.println(columnNames.size());
            if (result.getResult().hasNext()) {
                System.out.println("Usao");
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                System.out.println(rdfNode);
                return rdfNode;
            }
            return null;
        }
    }

    @Override
    public InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje) throws Exception {
        String documentId = UUID.randomUUID().toString();

        interesovanjeZaVakcinisanje.setAbout("http://www.ftn.uns.ac.rs/rdf/interesovanje/" + documentId);
        interesovanjeZaVakcinisanje.setTypeof("pred:IdentifikatorDokumenta");
        interesovanjeZaVakcinisanje.getLicniPodaci().getJmbg().setProperty("pred:Kreirao");
        interesovanjeZaVakcinisanje.getLicniPodaci().getJmbg().setDatatype("xs:string");
        interesovanjeZaVakcinisanje.getPodaciOVakcinisanju().getTipVakcine().setProperty("pred:Zeljena");
        interesovanjeZaVakcinisanje.getPodaciOVakcinisanju().getTipVakcine().setDatatype("xs:string");
        interesovanjeZaVakcinisanje.getDatumPodnosenja().setProperty("pred:Kreiran");
        interesovanjeZaVakcinisanje.getDatumPodnosenja().setDatatype("xs:string");
        interesovanjeZaVakcinisanje.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/");
        interesovanjeZaVakcinisanje.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        baseRepository.save("db/interesovanje", documentId, interesovanjeZaVakcinisanje, InteresovanjeZaVakcinisanje.class);
        OutputStream outputStream = jaxBParser.marshall(interesovanjeZaVakcinisanje, InteresovanjeZaVakcinisanje.class);
        String path = "gen/pdf/" + documentId + ".pdf";
        xslfoTransformer.generatePDF(outputStream.toString(), "data/xsl_fo/interesovanje.xsl", path);
        this.emailService.sendMail(interesovanjeZaVakcinisanje.getLicniPodaci().getAdresaElektronskePoste(), path);


        XMLResource resource = existDbManager.load("/db/interesovanje", documentId);

        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "interesovanje");
        return interesovanjeZaVakcinisanje;
    }


    @Override
    public ArrayList<RDFNode> searchRDF(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Kreirao> \"" + jmbg + "\" ;";

        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/interesovanje", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                System.out.println(columnNames.get(0));
                nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }

    @Override
    public ArrayList<InteresovanjeZaVakcinisanje> searchByJMBG(String jmbg) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(jmbg);
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = findById(parts[parts.length - 1]);
            interesovanja.add(interesovanjeZaVakcinisanje);
        }
        return interesovanja;
    }
    
    @Override
    public byte[] generateIntersovanjeToXHTML(String id) throws Exception {
    	InteresovanjeZaVakcinisanje interesovanje = findById(id);
    	return htmlTransformer.generateHTMLtoByteArray(interesovanje);
    }
    
    @Override
    public byte[] generateIntersovanjeToPDF(String id) throws Exception {
    	InteresovanjeZaVakcinisanje interesovanje = findById(id);
    	return xslfoTransformer.generatePDFtoByteArray(interesovanje);
    }

    @Override

    public ArrayList<InteresovanjeZaVakcinisanje> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception {
        String value = metadataSearchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/interesovanje", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<InteresovanjeZaVakcinisanje> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = findById(parts[parts.length - 1]);
            list.add(interesovanjeZaVakcinisanje);
        }
        return list;
    }
    public ArrayList<InteresovanjeZaVakcinisanje> searchByText(String search) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        String xqueryPath = "data/xquery/pretraga_po_tekstu_interesovanje.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, search);
        System.out.println(formattedXQueryExpresion);
        List<Resource> resources =
                existDbManager.executeXquery("/db/interesovanje", "http://www.ftn.uns.ac.rs/interesovanje",formattedXQueryExpresion);
        System.out.println(resources.size());
        ArrayList<InteresovanjeZaVakcinisanje> interesovanjeZaVakcinisanjes =  new ArrayList<InteresovanjeZaVakcinisanje>();
        for(Resource resource:resources){
            XMLResource xmlResource  = (XMLResource) resource;
            interesovanjeZaVakcinisanjes.add((InteresovanjeZaVakcinisanje) jaxBParser.unmarshall(xmlResource,InteresovanjeZaVakcinisanje.class));
        }
        return  interesovanjeZaVakcinisanjes;

    }

    @Override
    public InteresovanjeZaVakcinisanje update(InteresovanjeZaVakcinisanje entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) {

    }
    
    @Override
    public int getNumberOfInterestedPatients(String startDate, String endDate) throws IOException {
    	String sparqlCondition = "?s <http://www.ftn.uns.ac.rs/rdf/interesovanje/predicate/Kreiran> ?date. "
				+ "FILTER ( ?date >= \"" + startDate + "\" && ?date < \"" + endDate + "\")." ;
        try(RDFReadResult result = FusekiReader.readRDFWithSparqlCountQuery("/interesovanje", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                return rdfNode.asLiteral().getInt();
            }
        }
        
        return 0;
    }

    @Override
    public String readMetadata(String documentId, String format) throws IOException {
        String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/interesovanje/" + documentId + "> ?d ?s .";
        try {
            return FusekiReader.readMetadata("/interesovanje", sparqlCondition, format);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MissingEntityException("Ne postoji interesovanje sa tim id.");
        }
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public ArrayList<InteresovanjeZaVakcinisanje> searchMetadataLogical(String search) throws Exception {
        ArrayList<RDFNode> nodes = searchMETA(search);
        ArrayList<InteresovanjeZaVakcinisanje> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            InteresovanjeZaVakcinisanje interesovanje = findById(parts[parts.length - 1]);
            list.add(interesovanje);
        }
        return list;
    }

    public ArrayList<RDFNode> searchMETA(String sparqlCondition) throws IOException {
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/interesovanje", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }
}
