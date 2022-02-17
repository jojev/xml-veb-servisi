package main.java.com.xml.userbackend.service.contract;

import java.util.ArrayList;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;

public interface ISaglasnostService extends    IService<ObrazacZaSprovodjenjeImunizacije>{
    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik) throws Exception;

	byte[] generateSaglasnostToXHTML(String id) throws Exception;

	ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByJMBG(SearchDTO searchDTO) throws Exception;
}
