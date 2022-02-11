package main.java.com.xml.officialbackend.rdf;

import net.sf.saxon.TransformerFactoryImpl;
import org.springframework.stereotype.Component;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class MetadataExtractor {

    private TransformerFactory transformerFactory;

    private static final String XSLT_FILE = "data/xsl/grddl.xsl";

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
        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));


        Transformer transformer = transformerFactory.newTransformer(transformSource);


        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");


        StreamSource source = new StreamSource(new FileInputStream(in));


        ByteArrayOutputStream result = new ByteArrayOutputStream();
        transformer.transform(source, new StreamResult(result));

        return result.toByteArray();

    }

    public byte[] extractMetadataFromXmlContent(String xmlContent) throws TransformerException, IOException {
        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));


        Transformer transformer = transformerFactory.newTransformer(transformSource);


        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource source = new StreamSource(new ByteArrayInputStream(xmlContent.getBytes()));

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        transformer.transform(source, new StreamResult(result));

        return result.toByteArray();
    }

}
