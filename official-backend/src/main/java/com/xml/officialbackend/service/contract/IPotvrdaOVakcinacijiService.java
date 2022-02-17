package main.java.com.xml.officialbackend.service.contract;


import main.java.com.xml.officialbackend.dto.SearchDTO;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;

import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public interface IPotvrdaOVakcinacijiService extends IService<PotvrdaOVakcinaciji> {

    ArrayList<PotvrdaOVakcinaciji> findPotvrdeByJMBG(String jmbg) throws Exception;

    ArrayList<PotvrdaOVakcinaciji> searchByText(SearchDTO search) throws Exception;

    ArrayList<PotvrdaOVakcinaciji> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception;

    PotvrdaOVakcinaciji create(PotvrdaOVakcinaciji entity, String accessToken) throws Exception;

}
