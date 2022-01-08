package main.java.com.xml.officialbackend.existdb;

import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

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
        File file = new File("./data/digitalni_sertifikat.xml");
        existDbManager.store("/db/digitalni_sertifikat", "2.xml", FileUtils.readFileToString(file, StandardCharsets.UTF_8));
        XMLResource resource = existDbManager.load("/db/digitalni_sertifikat", "2.xml");

        DigitalniZeleniSertifikat interesovanje = jaxBParser.unmarshall(resource, DigitalniZeleniSertifikat.class);
        interesovanje.getLicniPodaci().setImePrezime("Imeee ii Prezimeeee");

        OutputStream os = jaxBParser.marshall(DigitalniZeleniSertifikat.class, interesovanje);

        existDbManager.store("/db/digitalni_sertifikat", "2.xml", os.toString());
    }
}
