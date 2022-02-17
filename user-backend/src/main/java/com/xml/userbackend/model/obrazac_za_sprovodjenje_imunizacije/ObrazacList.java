package main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ObrazacZaSprovodjenjeImunizacije.class})
public class ObrazacList {
    private List<ObrazacZaSprovodjenjeImunizacije> listOfEntityObjects;

    public ObrazacList() {
        listOfEntityObjects = new ArrayList<ObrazacZaSprovodjenjeImunizacije>();
    }

    public ObrazacList(List<ObrazacZaSprovodjenjeImunizacije> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<ObrazacZaSprovodjenjeImunizacije> getItems() {
        return listOfEntityObjects;
    }
}

