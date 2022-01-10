package main.java.com.xml.officialbackend.existdb;

import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class Test {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;

    public void test() throws Exception {

        File file = new File("data/documents/interesovanje.xml");

        existDbManager.store("/db/interesovanje", "2.xml", FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        XMLResource resource = existDbManager.load("/db/interesovanje", "2.xml");

        InteresovanjeZaVakcinisanje interesovanje = jaxBParser.unmarshall(resource, InteresovanjeZaVakcinisanje.class);

        OutputStream os = jaxBParser.marshall(interesovanje);

        existDbManager.store("/db/interesovanje", "2.xml", os.toString());

        /*
        try(RDFReadResult result = FusekiReader.readRDF("/test")) {
            ResultSetFormatter.outputAsXML(result.getResult());
        } */
    }
}
