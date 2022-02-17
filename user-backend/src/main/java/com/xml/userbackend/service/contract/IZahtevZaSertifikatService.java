package main.java.com.xml.userbackend.service.contract;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.apache.jena.rdf.model.RDFNode;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public interface IZahtevZaSertifikatService extends IService<ZahtevZaIzdavanjeSertifikata> {

    ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata, String accessToken) throws Exception;

    ZahtevZaIzdavanjeSertifikata setOdgovor(RazlogDTO razlogDTO) throws Exception;

    int getNumberOfRequestForDigitalSertificate(String startDate, String endDate) throws IOException;

    ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException;

    ArrayList<ZahtevZaIzdavanjeSertifikata> searchByJMBG(SearchDTO searchDTO) throws Exception;

	  byte[] generateZahtevToXHTML(String id) throws Exception;

    ArrayList<ZahtevZaIzdavanjeSertifikata> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception;

    String getByJmbg(String jmbg) throws IOException;

    ArrayList<ZahtevZaIzdavanjeSertifikata> searchByText(String search) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    ArrayList<ZahtevZaIzdavanjeSertifikata> findPendingZahtevi() throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;
}
