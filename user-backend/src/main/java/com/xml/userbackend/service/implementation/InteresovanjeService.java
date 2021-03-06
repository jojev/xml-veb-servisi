package main.java.com.xml.userbackend.service.implementation;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.lista_cekanja.ListaCekanja.Stavka;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.EmailService;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.transformations.HtmlTransformer;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;



@Service
public class InteresovanjeService implements IInteresovanjeService {

    private BaseRepository baseRepository;

    private EmailService emailService;

    private XSLFOTransformer xslfoTransformer;
    
    private HtmlTransformer htmlTransformer;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;
    
    private RestTemplate restTemplate;
    
    private SearchService searchService;


    @Autowired
    public InteresovanjeService(BaseRepository baseRepository, EmailService emailService, XSLFOTransformer xslfoTransformer, HtmlTransformer htmlTransformer,
                                JaxBParser jaxBParser, ExistDbManager existDbManager, MetadataExtractor metadataExtractor, RestTemplate restTemplate, SearchService searchService) {
        this.baseRepository = baseRepository;
        this.emailService = emailService;
        this.htmlTransformer = htmlTransformer;
        this.xslfoTransformer = xslfoTransformer;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.restTemplate = restTemplate;
        this.searchService = searchService;

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
    public InteresovanjeZaVakcinisanje getInteresovanjByJmbg(String jmbg) throws Exception {
    	RDFNode node = this.getInteresovanje(jmbg);
        String[] parts = node.toString().split("/");
        InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = findById(parts[parts.length - 1]);
        return interesovanjeZaVakcinisanje;
    }
    @Override
    public InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje, String accessToken) throws Exception {
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

        Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		
        Stavka stavka = new Stavka();
        stavka.setDoza(1);
        stavka.setEmailPacijenta(interesovanjeZaVakcinisanje.getLicniPodaci().getAdresaElektronskePoste());
        stavka.setJmbgPacijenta(interesovanjeZaVakcinisanje.getLicniPodaci().getJmbg().getValue());
        stavka.setTipVakcine(interesovanjeZaVakcinisanje.getPodaciOVakcinisanju().getTipVakcine().getValue().value());
        stavka.setPeriodCekanja(DatatypeFactory.newInstance().newXMLGregorianCalendar(strDate));
        
        
        System.out.println(stavka.getJmbgPacijenta() + " " + stavka.getEmailPacijenta() + " " + stavka.getDoza() + " " + stavka.getTipVakcine() + " " + stavka.getPeriodCekanja());
        
        
        XMLResource resource = existDbManager.load("/db/interesovanje", documentId);

        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "interesovanje");
        
        this.sendStavkaToOfficialBackend(stavka, accessToken);
        return interesovanjeZaVakcinisanje;
    }


    private void sendStavkaToOfficialBackend(Stavka stavka, String accessToken) {
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        String url = String.format("http://localhost:8081/api/v1/termini/add?periodCekanja=%1$s&jmbg=%2$s&email=%3$s&tipVakcine=%4$s&doza=%5$d", 
        		stavka.getPeriodCekanja(), stavka.getJmbgPacijenta(), stavka.getEmailPacijenta(), stavka.getTipVakcine(), stavka.getDoza());

        ResponseEntity<Object> entity = restTemplate.exchange(url, HttpMethod.GET,httpEntity, Object.class);
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

	@Override
	public InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


    @Override
    public String findWhereIsReferenced(String documentId) {
        String sparqlCondition = "?person ?predicate\"" + documentId + "\" .";

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/saglasnosti", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                return rdfNode.toString();
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

}
