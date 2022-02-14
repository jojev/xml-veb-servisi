package main.java.com.xml.userbackend.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "razlog",
        "odobren",
        "zahtev"
})
@XmlRootElement(name = "razlogdto")
public class RazlogDTO {

    @XmlElement()
    private String razlog;

    @XmlElement(required = true)
    private Boolean odobren;

    @XmlElement(required = true)
    private String zahtev;

    public RazlogDTO() {
    }

    public RazlogDTO(String razlog, Boolean odobren, String zahtev) {
        this.razlog = razlog;
        this.odobren = odobren;
        this.zahtev = zahtev;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public Boolean getOdobren() {
        return odobren;
    }

    public void setOdobren(Boolean odobren) {
        this.odobren = odobren;
    }

    public String getZahtev() {
        return zahtev;
    }

    public void setZahtev(String zahtev) {
        this.zahtev = zahtev;
    }
}