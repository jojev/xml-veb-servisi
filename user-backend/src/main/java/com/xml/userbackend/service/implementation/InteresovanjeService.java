package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.EmailService;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class InteresovanjeService implements IInteresovanjeService {

    private BaseRepository baseRepository;

    private EmailService emailService;

    private XSLFOTransformer xslfoTransformer;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;



    @Autowired
    public InteresovanjeService(BaseRepository baseRepository, EmailService emailService, XSLFOTransformer xslfoTransformer,
                                JaxBParser jaxBParser, ExistDbManager existDbManager, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.emailService = emailService;
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
        return null;
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

        baseRepository.save("db/interesovanje", documentId ,interesovanjeZaVakcinisanje);
        OutputStream outputStream = jaxBParser.marshall(interesovanjeZaVakcinisanje);
        String path = "gen/pdf/"+documentId+".pdf";
        xslfoTransformer.generatePDF(outputStream.toString(),"data/xsl_fo/interesovanje.xsl", path);
        this.emailService.sendMail(interesovanjeZaVakcinisanje.getLicniPodaci().getAdresaElektronskePoste(), path);


        XMLResource resource = existDbManager.load("/db/interesovanje", documentId);

        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "interesovanje");
        return interesovanjeZaVakcinisanje;
    }

    @Override
    public InteresovanjeZaVakcinisanje update(InteresovanjeZaVakcinisanje entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }
}
