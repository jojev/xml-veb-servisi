package main.java.com.xml.officialbackend.service.contract;


import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;

import java.util.ArrayList;
import java.util.List;

public interface IObrazacZaSprovodjenjeImunizacijeService extends IService<ObrazacZaSprovodjenjeImunizacije> {

    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaci, String accessToken) throws Exception;
    
    ObrazacZaSprovodjenjeImunizacije findByJMBG(String accessToken, String jmbg) throws Exception;


}

