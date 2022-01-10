package main.java.com.xml.officialbackend.rdf;

import net.sf.saxon.TransformerFactoryImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class MetadataExtractor {

    private TransformerFactory transformerFactory;

    private static final String XSLT_FILE = "data/xsl/grddl.xsl";
    private static final String RDF_FILE = "data/rdf/rdfOutput.rdf";

    public MetadataExtractor() {

        transformerFactory = new TransformerFactoryImpl();
    }

    /**
     * Generates RDF/XML based on RDFa metadata from an XML containing
     * input stream by applying GRDDL XSL transformation.
     *
     * @param in XML containing input stream
     * @return
     */
    public byte[] extractMetadata(String in) throws TransformerException, IOException {

        OutputStream out = new FileOutputStream(new File(RDF_FILE));


        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));


        Transformer transformer = transformerFactory.newTransformer(transformSource);


        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");


        StreamSource source = new StreamSource(new FileInputStream(in));


        ByteArrayOutputStream result = new ByteArrayOutputStream();
        transformer.transform(source, new StreamResult(result));
        result.writeTo(out);

        return result.toByteArray();

    }

    public void test() throws Exception {

        System.out.println("[INFO] " + MetadataExtractor.class.getSimpleName());

        //InputStream in = new FileInputStream(new File("data/contacts.xml"));

        byte[] out =  extractMetadata("data/documents/interesovanje.xml");
        System.out.println(out);

    }

    public static void main(String[] args) throws Exception {
        new MetadataExtractor().test();
    }
}
