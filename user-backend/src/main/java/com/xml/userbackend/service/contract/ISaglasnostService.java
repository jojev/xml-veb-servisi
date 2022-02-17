package main.java.com.xml.userbackend.service.contract;

import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import org.apache.jena.rdf.model.RDFNode;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;

public interface ISaglasnostService extends    IService<ObrazacZaSprovodjenjeImunizacije>{
    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik) throws Exception;

    public RDFNode getSaglasnostIdFromJMBG(String jmbg) throws IOException;
}
