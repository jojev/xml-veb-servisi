package main.java.com.xml.officialbackend.jaxb;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

@Component
public class JaxBParser {
    public <T> T unmarshall(XMLResource resource, Class genericClass) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

    public <T> OutputStream marshall(Class genericClass,T objectToMarshall) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(objectToMarshall, os);
        return os;
    }
}
