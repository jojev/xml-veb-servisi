package main.java.com.xml.userbackend.service.contract;

import java.util.ArrayList;

import main.java.com.xml.userbackend.dto.SearchDTO;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;

import java.util.ArrayList;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;

public interface ISaglasnostService extends    IService<ObrazacZaSprovodjenjeImunizacije>{
    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik) throws Exception;


	  byte[] generateSaglasnostToXHTML(String id) throws Exception;

	  ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(SearchDTO searchDTO) throws Exception;

    //ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(String jmbg) throws Exception;

    ArrayList<ObrazacZaSprovodjenjeImunizacije> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception;

    String getByDopunjenDatuma(XMLGregorianCalendar calendar) throws IOException;


	byte[] generateSaglasnostToPDF(String id) throws Exception;
}
