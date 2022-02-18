package main.java.com.xml.userbackend.transformations;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.transformations.XSLFOTransformer;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;

@Component
public class XSLFOTransformer {

	@Autowired
	private JaxBParser parser;
	
    private FopFactory fopFactory;

    private TransformerFactory transformerFactory;
    private static HashMap<Class, String> shemaLocationRegistry = new HashMap<>();

    static {
    	
    	shemaLocationRegistry.put(DigitalniZeleniSertifikat.class, "./data/xsl_fo/digitalni_sertifikat.xsl");
        shemaLocationRegistry.put(InteresovanjeZaVakcinisanje.class, "./data/xsl_fo/interesovanje.xsl");
        shemaLocationRegistry.put(ObrazacZaSprovodjenjeImunizacije.class, "./data/xsl_fo/obrazac_za_sprovodjenje_imunizacije.xsl");
        shemaLocationRegistry.put(ZahtevZaIzdavanjeSertifikata.class, "./data/xsl_fo/zahtev_za_izdavanje_sertifikata.xsl");
        shemaLocationRegistry.put(PotvrdaOVakcinaciji.class, "./data/xsl_fo/potvrda_o_vakcinaciji.xsl");
        shemaLocationRegistry.put(IzvestajOImunizaciji.class, "./data/xsl_fo/izvestaj_o_imunizaciji.xsl");
    }
    public XSLFOTransformer() throws SAXException, IOException {

        // Initialize FOP factory object
        fopFactory = FopFactory.newInstance(new File("src/main/java/com/xml/userbackend/fop.xconf"));

        // Setup the XSLT transformer factory
        transformerFactory = new TransformerFactoryImpl();
    }

    public void generatePDF(String xmlContent, String XSL_FILE, String OUTPUT_FILE) throws Exception {


        System.out.println("[INFO] " + XSLFOTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = new File(XSL_FILE);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject

        InputStream targetStream = new ByteArrayInputStream(xmlContent.getBytes());

        StreamSource source = new StreamSource(targetStream);

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = new File(OUTPUT_FILE);
        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

    }

    public <T> byte[] generatePDFtoByteArray(T object) throws Exception {

        System.out.println("[INFO] " + XSLFOTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        String xsl_fo = shemaLocationRegistry.get(object.getClass());

        // Create transformation source
        StreamSource transformSource = new StreamSource(xsl_fo);

        // Initialize the transformation subject
        ByteArrayOutputStream out = (ByteArrayOutputStream)parser.marshall(object, object.getClass());

        StreamSource source = new StreamSource(new ByteArrayInputStream(out.toByteArray()));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

    	return outStream.toByteArray();
     


    }
}
