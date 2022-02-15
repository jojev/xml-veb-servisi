package main.java.com.xml.officialbackend.model.zahtev_za_sertifikat;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ZahtevZaIzdavanjeSertifikata.class})
public class ZahtevList {
    private List<ZahtevZaIzdavanjeSertifikata> listOfEntityObjects;

    public ZahtevList() {
        listOfEntityObjects = new ArrayList<ZahtevZaIzdavanjeSertifikata>();
    }

    public ZahtevList(List<ZahtevZaIzdavanjeSertifikata> listOfEntityObjects) {
        this.listOfEntityObjects = listOfEntityObjects;
    }

    @XmlAnyElement
    public List<ZahtevZaIzdavanjeSertifikata> getItems() {
        return listOfEntityObjects;
    }
}
