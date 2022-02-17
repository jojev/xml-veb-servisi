package main.java.com.xml.officialbackend.test;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Doza;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Jmbg;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Osoba;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji.DatumIzdavanjaPotvrde;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Vakcinacija;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.Vakcinacija.Doze;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.contract.IZahtevZaSertifikatService;

import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Component
public class Test {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;
    
    @Autowired
    private MetadataExtractor metadataExtractor;

    @Autowired
    private BaseRepository baseRepository;
    
    @Autowired
    private IDigitalniSertifikatService zeleniService;
    
    @Autowired 
    private IPotvrdaOVakcinacijiService potvrdaService;

    public void test() throws Exception {
       //createDigitalni();
       //createPotvrdu();


    }

    public void testWriteToExistDb() throws Exception {
        File file = new File("data/documents/obrazac_za_sprovodjenje_imunizacije.xml");
        baseRepository.save("/db/obrazac_za_sprovodjenje_imunizacije", "1", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
    }
    
    public void createDigitalni() throws Exception {
    	File file = new File("data/documents/digitalni_sertifikat.xml");

        existDbManager.store("/db/digitalnii", "2412998125026.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/digitalnii", "2412998125026.xml");

        DigitalniZeleniSertifikat k = jaxBParser.unmarshall(resource, DigitalniZeleniSertifikat.class);


        zeleniService.create(k,"2412998125026", "filipovic.dada@gmail.com");
    }
    public void testWriteToRdf() throws IOException, TransformerException {
        byte[] out =  metadataExtractor.extractMetadata("data/documents/obrazac_za_sprovodjenje_imunizacije.xml");
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "obrazac_za_sprovodjenje_imunizacije");

    }
    
    public void createPotvrdu() throws IOException, Exception {
    	File file = new File("data/documents/potvrda_o_vakcinaciji.xml");

        existDbManager.store("/db/potvrdaa", "2412998125026.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/potvrdaa", "2412998125026.xml");

        PotvrdaOVakcinaciji k = jaxBParser.unmarshall(resource, PotvrdaOVakcinaciji.class);

        potvrdaService.create(k);
    }

//    public void testWriteToExistDb() throws Exception {
//        File file = new File("data/documents/obrazac_za_sprovodjenje_imunizacije.xml");
//        baseRepository.save("/db/obrazac_za_sprovodjenje_imunizacije", "1.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
//    }
//
//    public void testReadFromExistDb() throws Exception {
//        DigitalniZeleniSertifikat digitalniZeleniSertifikat = baseRepository.findById("/db/digitalni_sertifikat", "2.xml", DigitalniZeleniSertifikat.class);
//        System.out.println(digitalniZeleniSertifikat);
//    }
//
//    public void testUpdateDocumentFromExistDb() throws Exception {
//        InteresovanjeZaVakcinisanje interesovanje = baseRepository.findById("/db/potvrda_o_vakcinaciji", "1.xml", InteresovanjeZaVakcinisanje.class);
//        interesovanje.getLicniPodaci().setIme("Novo ime");
//        baseRepository.save("/db/interesovanje", "1.xml", interesovanje, InteresovanjeZaVakcinisanje.class);
//    }
//
//    public void testWriteToRdf() throws IOException, TransformerException {
//        byte[] out =  metadataExtractor.extractMetadata("data/documents/potvrda_o_vakcinaciji.xml");
//        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "potvrda_o_vakcinaciji");
//    }
//
//    public void testReadFromRdf() throws IOException {
//        try(RDFReadResult result = FusekiReader.readRDF("/obrazac_za_sprovodjenje_imunizacije")) {
//            ResultSetFormatter.outputAsXML(result.getResult());
//            for (String var: result.getResult().getResultVars()) {
//                System.out.println(var);
//            }
//        }
//    }
}
