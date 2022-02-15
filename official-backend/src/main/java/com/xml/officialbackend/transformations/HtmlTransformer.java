package main.java.com.xml.officialbackend.transformations;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Component;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class HtmlTransformer {
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    //public static final String HTML_FILE = "gen/html/potvrda_o_vakcinaciji.html";

    public static final String INPUT_FILE = "data/documents/potvrda_o_vakcinaciji.xml";

    public static final String XSL_FILE = "data/xslt/potvrda_o_vakcinaciji.xsl";


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

//    public static void main(String[] args) throws IOException, DocumentException {
//        HtmlTransformer htmlTransformer = new HtmlTransformer();
//
//        htmlTransformer.generateHTML(INPUT_FILE, XSL_FILE);
//    }
}
