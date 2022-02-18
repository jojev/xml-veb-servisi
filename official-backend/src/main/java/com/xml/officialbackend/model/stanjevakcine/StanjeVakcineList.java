package main.java.com.xml.officialbackend.model.stanjevakcine;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({StanjeVakcine.class})
public class StanjeVakcineList {
    private List<StanjeVakcine> listOfEntityObjects;

    public StanjeVakcineList() {
        listOfEntityObjects = new ArrayList<StanjeVakcine>();
    }

    public StanjeVakcineList(List<StanjeVakcine> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<StanjeVakcine> getItems() {
        return listOfEntityObjects;
    }
}