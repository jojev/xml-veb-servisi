package main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({PotvrdaOVakcinaciji.class})
public class PotvrdaOVakcinacijiList {
    private List<PotvrdaOVakcinaciji> listOfEntityObjects;

    public PotvrdaOVakcinacijiList() {
        listOfEntityObjects = new ArrayList<PotvrdaOVakcinaciji>();
    }

    public PotvrdaOVakcinacijiList(List<PotvrdaOVakcinaciji> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<PotvrdaOVakcinaciji> getItems() {
        return listOfEntityObjects;
    }
}