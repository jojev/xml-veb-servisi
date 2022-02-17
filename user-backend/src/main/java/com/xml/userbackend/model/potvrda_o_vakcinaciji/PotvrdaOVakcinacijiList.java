package main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(PotvrdaOVakcinaciji.class)
public class PotvrdaOVakcinacijiList {
    @XmlElement(name="ListOfEntityObjects")
    private List<PotvrdaOVakcinaciji> listOfEntityObjects;

    public PotvrdaOVakcinacijiList() {
        listOfEntityObjects = new ArrayList<PotvrdaOVakcinaciji>();
    }

    public PotvrdaOVakcinacijiList(List<PotvrdaOVakcinaciji> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    public void setListOfEntityObjects(List<PotvrdaOVakcinaciji> list) {
        this.listOfEntityObjects = list;
    }

    @XmlAnyElement
    public List<PotvrdaOVakcinaciji> getItems() {
        return listOfEntityObjects;
    }
}
