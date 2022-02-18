package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.Doza;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.Test;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.apache.jena.rdf.model.RDFNode;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public interface IDigitalniSertifikatService extends IService<DigitalniZeleniSertifikat> {

    void send(ZahtevZaIzdavanjeSertifikata zahtev, ObrazacZaSprovodjenjeImunizacije obrazac, ArrayList<PotvrdaOVakcinaciji> potvrde, String accessToken) throws Exception;


    DigitalniZeleniSertifikat create(DigitalniZeleniSertifikat digitalniZeleniSertifikat, String documentId, String email, String accesToken) throws Exception;

    boolean check(ArrayList<Doza> doze, BigInteger redniBr);

    String findManufacturer(String name);

    ArrayList<Test> makeTests();

    ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException;

    ArrayList<DigitalniZeleniSertifikat> searchByJMBG(SearchDTO searchDTO) throws Exception;

	  byte[] generateDigitalniToXHTML(String id) throws Exception;

    ArrayList<DigitalniZeleniSertifikat> searchMetadata(MetadataSearchDTO searchDTO) throws Exception;

    String readMetadata(String documentId, String format) throws IOException;

    ArrayList<DigitalniZeleniSertifikat> searchByText(SearchDTO searchDTO) throws IOException, JAXBException, XMLDBException, SAXException, ClassNotFoundException, InstantiationException, IllegalAccessException;

	byte[] generateDigitalniToPDF(String id) throws Exception;

    ArrayList<DigitalniZeleniSertifikat> searchMetadataLogical(MetadataSearchDTO searchDTO) throws Exception;
}
