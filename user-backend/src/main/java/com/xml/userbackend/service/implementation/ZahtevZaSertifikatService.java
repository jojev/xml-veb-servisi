package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.digitalni_sertifikat.*;
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
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Service
public class ZahtevZaSertifikatService implements IZahtevZaSertifikatService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private EmailService emailService;

    private IInteresovanjeService interesovanjeService;


    @Autowired
    public ZahtevZaSertifikatService(BaseRepository baseRepository, JaxBParser jaxBParser,
                                     ExistDbManager existDbManager, MetadataExtractor metadataExtractor,
                                     EmailService emailService,IInteresovanjeService interesovanjeService) {
        this.baseRepository = baseRepository;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.emailService = emailService;
        this.interesovanjeService = interesovanjeService;

    }

    @Override
    public List<ZahtevZaIzdavanjeSertifikata> findAll() {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata findById(String id) throws Exception {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata update(ZahtevZaIzdavanjeSertifikata entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata) throws Exception {
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
        
        baseRepository.save("db/zahtev_za_sertifikat", documentId ,zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);

        OutputStream outputStream = jaxBParser.marshall(zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);


        XMLResource resource = existDbManager.load("/db/zahtev_za_sertifikat", documentId);

        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "zahtev_za_sertifikat");
        return zahtevZaIzdavanjeSertifikata;
    }

    @Override
    public RDFNode searchRDF(SearchDTO searchDTO) throws IOException {
        String jmbg = searchDTO.getSearch();
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoJmbg> \"" + jmbg + "\" ;";

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                return row.get(columnName);
            }

            return null;
        }
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata searchByJMBG(SearchDTO searchDTO) throws Exception {
        RDFNode documentId = searchRDF(searchDTO);
        String[] parts = documentId.toString().split("/");
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = baseRepository.findById("/db/zahtev_za_sertifikat", parts[parts.length-1],ZahtevZaIzdavanjeSertifikata.class);
        if(zahtevZaIzdavanjeSertifikata == null){
            throw new MissingEntityException("Ne postoji dokument sa prosledjenim identifikatorom.");
        }
        return zahtevZaIzdavanjeSertifikata;
    }

    @Override
    public void response(RazlogDTO razlogDTO) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtev = baseRepository.findById("/db/zahtev_za_sertifikat", razlogDTO.getZahtev(), ZahtevZaIzdavanjeSertifikata.class);
        InteresovanjeZaVakcinisanje interesovanje = interesovanjeService.searchByJMBG(zahtev.getPodnosilacZahteva().getJmbg().getValue());
        if(!razlogDTO.getOdobren()){
            emailService.sendResponse(interesovanje.getLicniPodaci().getAdresaElektronskePoste()," ", razlogDTO.getRazlog());
        }else {
            String documentId = UUID.randomUUID().toString();
            DigitalniZeleniSertifikat digitalniZeleniSertifikat = new DigitalniZeleniSertifikat();
            digitalniZeleniSertifikat.setAbout("http://www.ftn.uns.ac.rs/rdf/digitalni_sertifikat/" + documentId);
            digitalniZeleniSertifikat.setTypeof("pred:IdentifikatorDokumenta");
            // PODACI O SERTIFIKATU
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

            // LICNI PODACI
            LicniPodaci licniPodaci = new LicniPodaci();
            licniPodaci.getJmbg().setValue(interesovanje.getLicniPodaci().getJmbg().getValue());
            licniPodaci.getJmbg().setProperty("pred:ZatrazioJmbg");
            licniPodaci.getJmbg().setDatatype("xs:string");
            licniPodaci.getBrojPasosa().setProperty("pred:ZatrazioBrojPasosa");
            licniPodaci.getBrojPasosa().setDatatype("xs:string");
            //licniPodaci.setPol();
            //licniPodaci.setDatumRodjenja();

            licniPodaci.setImePrezime(interesovanje.getLicniPodaci().getIme() + " " + interesovanje.getLicniPodaci().getPrezime());
            digitalniZeleniSertifikat.setLicniPodaci(licniPodaci);

            // PODACI O VAKCINACIJI
            PodaciOVakcinaciji podaciOVakcinaciji = new PodaciOVakcinaciji();
            PodaciOVakcinaciji.Doze doze = new PodaciOVakcinaciji.Doze();
            ArrayList<Doza>  d = new ArrayList<>();
            Doza doza = new Doza();
           //SETOVATI DOZE IZ POTVRDE
            doze.setDoza(d);
            podaciOVakcinaciji.setDoze(doze);

        }
    }
}
