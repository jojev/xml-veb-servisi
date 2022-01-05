package main.java.com.xml.officialbackend.jaxb;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Component
public class JaxBParser {
    public <T> T unmarshall(XMLResource resource, Class genericClass) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T createdObject = (T) unmarshaller.unmarshal(resource.getContentAsDOM());
        return createdObject;
    }
}
