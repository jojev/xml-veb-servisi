package main.java.com.xml.userbackend.service.contract;

import java.util.ArrayList;

import main.java.com.xml.userbackend.dto.SearchDTO;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.apache.jena.rdf.model.RDFNode;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;

public interface ISaglasnostService extends    IService<ObrazacZaSprovodjenjeImunizacije>{
    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik) throws Exception;

    public RDFNode getSaglasnostIdFromJMBG(String jmbg) throws IOException;
  
	  byte[] generateSaglasnostToXHTML(String id) throws Exception;

	  ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(SearchDTO searchDTO) throws Exception;

    //ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(String jmbg) throws Exception;

    ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByText(SearchDTO searchDTO) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;

    ArrayList<ObrazacZaSprovodjenjeImunizacije> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception;

    String getByDopunjenDatuma(XMLGregorianCalendar calendar) throws IOException;


	byte[] generateSaglasnostToPDF(String id) throws Exception;

    ArrayList<ObrazacZaSprovodjenjeImunizacije> searchMetadataLogical(String search) throws Exception;

}
