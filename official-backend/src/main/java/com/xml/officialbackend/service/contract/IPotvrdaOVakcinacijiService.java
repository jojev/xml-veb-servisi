package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;

import java.io.IOException;
import java.util.ArrayList;

public interface IPotvrdaOVakcinacijiService extends IService<PotvrdaOVakcinaciji> {

    ArrayList<PotvrdaOVakcinaciji> findPotvrdeByJMBG(String jmbg) throws Exception;
}
