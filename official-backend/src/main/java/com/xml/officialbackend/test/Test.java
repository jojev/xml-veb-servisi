package main.java.com.xml.officialbackend.test;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class Test {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;

    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private MetadataExtractor metadataExtractor;

    public void test() throws Exception {

        File file = new File("data/documents/interesovanje.xml");

        existDbManager.store("/db/interesovanje", "2.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/interesovanje", "2.xml");

        InteresovanjeZaVakcinisanje interesovanje = jaxBParser.unmarshall(resource, InteresovanjeZaVakcinisanje.class);

        OutputStream os = jaxBParser.marshall(interesovanje, InteresovanjeZaVakcinisanje.class);

        existDbManager.store("/db/interesovanje", "2.xml", os.toString());

        /*
        try(RDFReadResult result = FusekiReader.readRDF("/test")) {
            ResultSetFormatter.outputAsXML(result.getResult());
        } */
    }

    public void testWriteToExistDb() throws Exception {
        File file = new File("data/documents/interesovanje.xml");
        baseRepository.save("/db/interesovanje", "1.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
    }

    public void testReadFromExistDb() throws Exception {
        InteresovanjeZaVakcinisanje interesovanje = baseRepository.findById("/db/interesovanje", "1.xml", InteresovanjeZaVakcinisanje.class);
        System.out.println(interesovanje);
    }

    public void testUpdateDocumentFromExistDb() throws Exception {
        InteresovanjeZaVakcinisanje interesovanje = baseRepository.findById("/db/interesovanje", "1.xml", InteresovanjeZaVakcinisanje.class);
        interesovanje.getLicniPodaci().setIme("Novo ime");
        baseRepository.save("/db/interesovanje", "1.xml", interesovanje, InteresovanjeZaVakcinisanje.class);
    }

    public void testWriteToRdf() throws IOException, TransformerException {
        byte[] out =  metadataExtractor.extractMetadata("data/documents/interesovanje.xml");
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "test");
    }

    public void testReadFromRdf() throws IOException {
        try(RDFReadResult result = FusekiReader.readRDF("/test")) {
            ResultSetFormatter.outputAsXML(result.getResult());
        }
    }
}
