package main.java.com.xml.userbackend.jaxb;


import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import main.java.com.xml.userbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.util.ShemaValidationHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;

@Component
public class JaxBParser {
    private HashMap<Class, String> shemaLocationRegistry = new HashMap<>();

    public JaxBParser() {
        shemaLocationRegistry.put(DigitalniZeleniSertifikat.class, "./data/schemes/digitalni_sertifikat.xsd");
        shemaLocationRegistry.put(InteresovanjeZaVakcinisanje.class, "./data/schemes/interesovanje.xsd");
        shemaLocationRegistry.put(IzvestajOImunizaciji.class, "./data/schemes/izvestaj_o_imunizaciji.xsd");
        shemaLocationRegistry.put(ObrazacZaSprovodjenjeImunizacije.class, "./data/schemes/obrazac_za_sprovodjenje_imunizacije.xsd");
        shemaLocationRegistry.put(PotvrdaOVakcinaciji.class, "./data/schemes/potvrda_o_vakcinaciji.xsd");
        shemaLocationRegistry.put(ZahtevZaIzdavanjeSertifikata.class, "./data/schemes/zahtev_za_sertifikat.xsd");
    }

    public <T> T unmarshall(XMLResource resource, Class genericClass) throws JAXBException, XMLDBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(shemaLocationRegistry.get(genericClass));
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new ShemaValidationHandler());

        return (T) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }

    public <T> OutputStream marshall(T objectToMarshall) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(objectToMarshall, os);
        return os;
    }
}
