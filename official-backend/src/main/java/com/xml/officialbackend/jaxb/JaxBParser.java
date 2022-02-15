package main.java.com.xml.officialbackend.jaxb;


import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.model.termin.Termin;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.util.ShemaValidationHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.*;
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
        shemaLocationRegistry.put(Korisnik.class, "./data/schemes/korisnik.xsd");
        shemaLocationRegistry.put(StanjeVakcine.class, "./data/schemes/stanjeVakcine.xsd");
        shemaLocationRegistry.put(Termin.class, "./data/schemes/termin.xsd");
        shemaLocationRegistry.put(ListaCekanja.class, "./data/schemes/lista_cekanja.xsd");
        shemaLocationRegistry.put(PoslednjiTermin.class, "./data/schemes/poslednji_termin.xsd");

    }

    public <T> T unmarshall(XMLResource resource, Class genericClass) throws JAXBException, XMLDBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(shemaLocationRegistry.get(genericClass));
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new ShemaValidationHandler());
        try {
            return (T) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
        }
        catch (Exception e) {
            throw new MissingEntityException("The entity with given id does not exist in the system.");
        }
    }

    public <T> OutputStream marshall(T objectToMarshall, Class genericClass) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass().getPackage().getName());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(shemaLocationRegistry.get(genericClass));
        Schema schema = schemaFactory.newSchema(schemaFile);
        marshaller.setSchema(schema);

        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(objectToMarshall, os);
        return os;
    }
}
