package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;

import java.io.IOException;

public interface IObrazacZaSprovodjenjeImunizacijeService extends IService<ObrazacZaSprovodjenjeImunizacije>{

    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception;

    ObrazacZaSprovodjenjeImunizacije findByJMBG(String jmbg) throws Exception;
}