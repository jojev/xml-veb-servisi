package main.java.com.xml.officialbackend.model.digitalni_sertifikat;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({DigitalniZeleniSertifikat.class})
public class DigitalniSertifikatList {
    private List<DigitalniZeleniSertifikat> listOfEntityObjects;

    public DigitalniSertifikatList() {
        listOfEntityObjects = new ArrayList<DigitalniZeleniSertifikat>();
    }

    public DigitalniSertifikatList(List<DigitalniZeleniSertifikat> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<DigitalniZeleniSertifikat> getItems() {
        return listOfEntityObjects;
    }
}