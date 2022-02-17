package main.java.com.xml.officialbackend.transformations;

import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;

import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;

@Component
public class HtmlTransformer {
	@Autowired
	private JaxBParser parser;
	
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    //public static final String HTML_FILE = "gen/html/potvrda_o_vakcinaciji.html";

    public static final String INPUT_FILE = "data/documents/potvrda_o_vakcinaciji.xml";

    public static final String XSL_FILE = "data/xslt/potvrda_o_vakcinaciji.xsl";


    private static HashMap<Class, String> shemaLocationRegistry = new HashMap<>();
    
    static {
    	
    	shemaLocationRegistry.put(DigitalniZeleniSertifikat.class, "./data/xslt/digitalni_sertifikat.xsl");
        shemaLocationRegistry.put(InteresovanjeZaVakcinisanje.class, "./data/xslt/interesovanje.xsl");
        shemaLocationRegistry.put(ObrazacZaSprovodjenjeImunizacije.class, "./data/xslt/obrazac_za_sprovodjenje_imunizacije.xsl");
        shemaLocationRegistry.put(ZahtevZaIzdavanjeSertifikata.class, "./data/xslt/zahtev_za_izdavanje_sertifikata.xsl");
        shemaLocationRegistry.put(PotvrdaOVakcinaciji.class, "./data/xslt/potvrda_o_vakcinaciji.xsl");
    }
    
    public HtmlTransformer(){}
    static {

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();

    }

    public org.w3c.dom.Document buildDocument(InputStream filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(filePath);

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public void generateHTML(String xmlContent, String xslPath, String htmlFile) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML

            InputStream targetStream = new ByteArrayInputStream(xmlContent.getBytes());

            //StreamSource source = new StreamSource(targetStream);


            DOMSource source = new DOMSource(buildDocument(targetStream));
            StreamResult result = new StreamResult(new FileOutputStream(htmlFile));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public <T> byte[] generateHTMLtoByteArray(T object) throws JAXBException, SAXException, IOException, ParserConfigurationException {

        try {
        	String xslPath = shemaLocationRegistry.get(object.getClass());
        	org.w3c.dom.Document document = null;
        	
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            OutputStream os = parser.marshall(object, object.getClass());

            ByteArrayOutputStream baos = (ByteArrayOutputStream)os;
            
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(baos.toByteArray()));
            // Transform DOM to HTML
            DOMSource source = new DOMSource(document);
            
            ByteArrayOutputStream xhtmlResult = new ByteArrayOutputStream();
			transformer.transform(source, new StreamResult(xhtmlResult));

			return xhtmlResult.toByteArray();
            

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
		return null;
    }

}
