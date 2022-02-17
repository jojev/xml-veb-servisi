package main.java.com.xml.officialbackend.service.contract;


import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;


import javax.xml.bind.JAXBException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public interface IObrazacZaSprovodjenjeImunizacijeService extends IService<ObrazacZaSprovodjenjeImunizacije>{

    ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                            PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception;
  
    List<ObrazacZaSprovodjenjeImunizacije> findByJMBG(String token, String jmbg) throws Exception;



    ArrayList<ObrazacZaSprovodjenjeImunizacije> searchByText(SearchDTO searchDTO) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;


}

