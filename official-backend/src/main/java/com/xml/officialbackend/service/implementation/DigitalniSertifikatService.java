package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.exception.BadLogicException;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.*;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.EmailService;
import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import main.java.com.xml.officialbackend.transformations.HtmlTransformer;
import main.java.com.xml.officialbackend.transformations.XSLFOTransformer;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

@Service
public class DigitalniSertifikatService implements IDigitalniSertifikatService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private EmailService emailService;

    private XSLFOTransformer xslfoTransformer;

    private HtmlTransformer htmlTransformer;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public DigitalniSertifikatService(BaseRepository baseRepository, JaxBParser jaxBParser,
                                      ExistDbManager existDbManager, MetadataExtractor metadataExtractor,
                                      EmailService emailService, XSLFOTransformer xslfoTransformer,
                                      HtmlTransformer htmlTransformer) {
        this.baseRepository = baseRepository;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.emailService = emailService;
        this.xslfoTransformer = xslfoTransformer;
        this.htmlTransformer = htmlTransformer;
    }

    @Override
    public List<DigitalniZeleniSertifikat> findAll() {
        return null;
    }

    @Override
    public DigitalniZeleniSertifikat findById(String id) throws Exception {
        return null;
    }

    @Override
    public DigitalniZeleniSertifikat create(DigitalniZeleniSertifikat entity) throws Exception {
        return null;
    }

    @Override
    public DigitalniZeleniSertifikat create(DigitalniZeleniSertifikat digitalniZeleniSertifikat, String documentId, String email, String accessToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/by-jmbg/" +
                digitalniZeleniSertifikat.getLicniPodaci().getJmbg().getValue(), HttpMethod.GET, httpEntity, String.class);

        String zahtevId = response.getBody();

        if (zahtevId == "") {
            throw new BadLogicException("Da bi se kreirao digitalni sertifikat korisnik mora da podnese zahtev.");
        }

        digitalniZeleniSertifikat.setZahtevZaSertifikatRef(new DigitalniZeleniSertifikat.ZahtevZaSertifikatRef());
        digitalniZeleniSertifikat.getZahtevZaSertifikatRef().setProperty("pred:Referencira");
        digitalniZeleniSertifikat.getZahtevZaSertifikatRef().setDatatype("xs:string");
        digitalniZeleniSertifikat.getZahtevZaSertifikatRef().setValue(zahtevId);

        baseRepository.save("db/digitalni_sertifikat", documentId, digitalniZeleniSertifikat, DigitalniZeleniSertifikat.class);
        OutputStream outputStream = jaxBParser.marshall(digitalniZeleniSertifikat, DigitalniZeleniSertifikat.class);
        String path = "gen/pdf/" + documentId + ".pdf";
        String htmlPath = "gen/html/" + documentId + ".html";
        xslfoTransformer.generatePDF(outputStream.toString(), "data/xsl-fo/digitalni_sertifikat.xsl", path);
        htmlTransformer.generateHTML(outputStream.toString(), "data/xslt/digitalni_sertifikat.xsl", htmlPath);

        emailService.sendResponse(email, path, htmlPath, " ");
        XMLResource resource = existDbManager.load("/db/digitalni_sertifikat", documentId);

        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "digitalni_sertifikat");
        return digitalniZeleniSertifikat;
    }

    @Override
    public DigitalniZeleniSertifikat update(DigitalniZeleniSertifikat entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public boolean check(ArrayList<Doza> doze, BigInteger redniBr) {
        for (Doza doza : doze) {
            if (Objects.equals(doza.getRedniBroj(), redniBr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String findManufacturer(String name) {
        if (name.equals("Pfizer-BioNTech")) {
            return "BIONTECH MANUFACTURING GMBH";
        } else if (name.equals("Sputnik V")) {
            return "Gamaleya Research Institute of Epidemiology and Microbiology";
        } else if (name.equals("Sinopharm")) {
            return "China National Pharmaceutical Group Co.";
        } else if (name.equals("AstraZeneca")) {
            return "Oxford";
        } else if (name.equals("Moderna")) {
            return "Moderna";
        }
        return "";
    }

    @Override
    public ArrayList<Test> makeTests() {
        ArrayList<Test> tests = new ArrayList<>();

        Test t1 = new Test();
        t1.setTip("SARS-CoV-2 RT Real-time PCR");

        Test t2 = new Test();
        t2.setTip("SARS-CoV-2 Ag-RDT(Antigen Rapid Detection test)");

        Test t3 = new Test();
        t3.setTip("SARS-CoV-2 RBD S-Protein Immunoglobulin G (IgG) test");

        tests.add(t1);
        tests.add(t2);
        tests.add(t3);
        for (Test test : tests) {
            test.setVrstaUzorka("N/A");
            test.setProizvodjacTesta("N/A");
            test.setDatumVremeUzorkovanja("N/A");
            test.setDatumVremeIzdavanjaRezultata("N/A");
            test.setRezultat("N/A");
            test.setLaboratorija("N/A");
        }
        return tests;
    }

    @Override
    public ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException {
        String jmbg = searchDTO.getSearch();
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/predicate/ZatrazioJmbg> \"" + jmbg + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/digitalni_sertifikat", sparqlCondition);) {
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
    public ArrayList<DigitalniZeleniSertifikat> searchByJMBG(SearchDTO searchDTO) throws Exception {
        ArrayList<DigitalniZeleniSertifikat> list = new ArrayList<>();
        ArrayList<RDFNode> nodes = searchRDF(searchDTO);
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            DigitalniZeleniSertifikat digitalniZeleniSertifikat = baseRepository.findById("/db/digitalni_sertifikat", parts[parts.length - 1], DigitalniZeleniSertifikat.class);
            list.add(digitalniZeleniSertifikat);
        }
        return list;
    }

    @Override
    public ArrayList<DigitalniZeleniSertifikat> searchMetadata(MetadataSearchDTO searchDTO) throws Exception {
        String value = searchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/digitalni_sertifikat", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<DigitalniZeleniSertifikat> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            DigitalniZeleniSertifikat digitalniZeleniSertifikat = baseRepository.findById("/db/digitalni_sertifikat", parts[parts.length - 1], DigitalniZeleniSertifikat.class);
            list.add(digitalniZeleniSertifikat);
        }
        return list;
    }

    @Override
    public ArrayList<DigitalniZeleniSertifikat> searchByText(SearchDTO searchDTO) throws IOException, JAXBException, XMLDBException, SAXException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String xqueryPath = "data/xquery/pretraga_po_tekstu_sertifikat.xqy";
        String xqueryExpression = readFile(xqueryPath, StandardCharsets.UTF_8);

        String formattedXQueryExpresion = String.format(xqueryExpression, searchDTO.getSearch());
        System.out.println(formattedXQueryExpresion);
        List<Resource> resources =
                existDbManager.executeXquery("/db/digitalni_sertifikat", "http://www.ftn.uns.ac.rs/digitalni_sertifikat",formattedXQueryExpresion);
        ArrayList<DigitalniZeleniSertifikat> digitalniZeleniSertifikats =  new ArrayList<DigitalniZeleniSertifikat>();
        for(Resource resource:resources){
            XMLResource xmlResource  = (XMLResource) resource;
            digitalniZeleniSertifikats.add((DigitalniZeleniSertifikat) jaxBParser.unmarshall(xmlResource,DigitalniZeleniSertifikat.class));
        }
        return  digitalniZeleniSertifikats;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public void send(ZahtevZaIzdavanjeSertifikata zahtev, ObrazacZaSprovodjenjeImunizacije obrazac, ArrayList<PotvrdaOVakcinaciji> potvrde, String accesToken) throws Exception {
        String u = UUID.randomUUID().toString();

        String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        String documentId = lUUID.substring(0, 6) + "/" + lUUID.substring(6, 8);
        DigitalniZeleniSertifikat digitalniZeleniSertifikat = new DigitalniZeleniSertifikat();
        digitalniZeleniSertifikat.setAbout("http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/" + u);
        digitalniZeleniSertifikat.setTypeof("pred:IdentifikatorDokumenta");


        digitalniZeleniSertifikat.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/predicate/");
        digitalniZeleniSertifikat.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        PodaciOSertifikatu podaciOSertifikatu = new PodaciOSertifikatu();
        podaciOSertifikatu.setBrojSertifikata(documentId);

        GregorianCalendar calendar = new GregorianCalendar();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        XMLGregorianCalendar datum = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        podaciOSertifikatu.setDatum(datum);

        PodaciOSertifikatu.DatumVremeIzdavanja datumVremeIzdavanja = new PodaciOSertifikatu.DatumVremeIzdavanja();
        datumVremeIzdavanja.setProperty("pred:Izdat");
        datumVremeIzdavanja.setDatatype("xs:string");
        datumVremeIzdavanja.setValue(datum);
        podaciOSertifikatu.setDatumVremeIzdavanja(datumVremeIzdavanja);

        digitalniZeleniSertifikat.setPodaciOSertifikatu(podaciOSertifikatu);

        DigitalniZeleniSertifikat.QrKod qrKod = new DigitalniZeleniSertifikat.QrKod();
        qrKod.setDatatype("xs:string");
        qrKod.setProperty("pred:Linkuje");
        //VREDNOST QR KODA????
        qrKod.setValue("");
        digitalniZeleniSertifikat.setQrKod(qrKod);


        LicniPodaci licniPodaci = new LicniPodaci();
        Jmbg jmbg = new Jmbg();
        jmbg.setDatatype("xs:string");
        jmbg.setProperty("pred:ZatrazioJmbg");
        jmbg.setValue(zahtev.getPodnosilacZahteva().getJmbg().getValue());
        licniPodaci.setJmbg(jmbg);
        BrojPasosa brojPasosa = new BrojPasosa();
        brojPasosa.setDatatype("xs:string");
        brojPasosa.setProperty("pred:ZatrazioBrojPasosa");
        brojPasosa.setValue(zahtev.getPodnosilacZahteva().getBrojPasosa().getValue());
        licniPodaci.setBrojPasosa(brojPasosa);
        if ((obrazac.getPodaciKojeJePopunioPacijent().getLicniPodaci().getPol().equals("Z"))) {
            licniPodaci.setPol("Zensko");
        } else {
            licniPodaci.setPol("Musko");
        }

        licniPodaci.setDatumRodjenja(obrazac.getPodaciKojeJePopunioPacijent().getLicniPodaci().getDatumRodjenja());
        licniPodaci.setImePrezime(zahtev.getPodnosilacZahteva().getImePrezime());
        digitalniZeleniSertifikat.setLicniPodaci(licniPodaci);


        PodaciOVakcinaciji podaciOVakcinaciji = new PodaciOVakcinaciji();
        PodaciOVakcinaciji.Doze doze = new PodaciOVakcinaciji.Doze();
        ArrayList<Doza> listDoza = new ArrayList<>();
        for (PotvrdaOVakcinaciji potvrda : potvrde) {
            for (main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Doza d : potvrda.getPodaciOVakcinaciji().getDoze().getDoza())
                if (!check(listDoza, d.getRedniBroj())) {
                    Doza doza = new Doza();
                    doza.setRedniBroj(d.getRedniBroj());
                    doza.setDatum(d.getDatumDavanja());
                    doza.setTip(potvrda.getPodaciOVakcinaciji().getNazivVakcine());

                    doza.setProizvodjacSerija(findManufacturer(doza.getTip()) + ", " + d.getSerija());
                    doza.setZdravstvenaUstanova("Dom zdravlja \"Novi Sad\", Novi Sad");
                    listDoza.add(doza);
                }
        }

        doze.setDoza(listDoza);
        podaciOVakcinaciji.setDoze(doze);

        digitalniZeleniSertifikat.setPodaciOVakcinaciji(podaciOVakcinaciji);

        DigitalniZeleniSertifikat.Testovi testovi = new DigitalniZeleniSertifikat.Testovi();
        testovi.setTest(makeTests());
        digitalniZeleniSertifikat.setTestovi(testovi);
        create(digitalniZeleniSertifikat, u, obrazac.getPodaciKojeJePopunioPacijent().getLicniPodaci().getImejl(), accesToken);
    }
    
    @Override
    public byte[] generateDigitalniToXHTML(String id) throws Exception {
    	DigitalniZeleniSertifikat sertifikat = findById(id);
    	return htmlTransformer.generateHTMLtoByteArray(sertifikat);
    }

}
