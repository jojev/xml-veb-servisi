package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.EmailService;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ZahtevZaSertifikatService implements IZahtevZaSertifikatService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;


    @Autowired
    public ZahtevZaSertifikatService(BaseRepository baseRepository, JaxBParser jaxBParser,
                                     ExistDbManager existDbManager, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;

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

        baseRepository.save("db/zahtev_za_sertifikat", documentId, zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);
        OutputStream outputStream = jaxBParser.marshall(zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);


        XMLResource resource = existDbManager.load("/db/zahtev_za_sertifikat", documentId);

        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "zahtev_za_sertifikat");
        return zahtevZaIzdavanjeSertifikata;
    }
    
    @Override
    public int getNumberOfRequestForDigitalSertificate(LocalDate startDate, LocalDate endDate) throws IOException {
    	String sparqlCondition = "?s <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/IzdatDatuma> ?date. "
				+ "FILTER ( ?date >= \"" + startDate + "\"^^<http://www.w3.org/2001/XMLSchema#date> && ?date < \"" + endDate + "\"^^<http://www.w3.org/2001/XMLSchema#date>)." ;

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlCountQuery("/zahtev_za_sertifikat", sparqlCondition);) {
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
}
