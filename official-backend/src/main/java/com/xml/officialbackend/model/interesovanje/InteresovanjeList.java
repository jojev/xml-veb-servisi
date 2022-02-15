package main.java.com.xml.officialbackend.model.interesovanje;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement
@XmlSeeAlso({InteresovanjeZaVakcinisanje.class})
public class InteresovanjeList {
    private List<InteresovanjeZaVakcinisanje> listOfEntityObjects;

    public InteresovanjeList() {
        listOfEntityObjects = new ArrayList<InteresovanjeZaVakcinisanje>();
    }

    public InteresovanjeList(List<InteresovanjeZaVakcinisanje> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<InteresovanjeZaVakcinisanje> getItems() {
        return listOfEntityObjects;
    }
}