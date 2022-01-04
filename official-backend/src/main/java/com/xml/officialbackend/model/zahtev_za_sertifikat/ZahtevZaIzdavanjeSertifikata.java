//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.05 at 12:22:27 AM CET 
//


package main.java.com.xml.officialbackend.model.zahtev_za_sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="podnosilac_zahteva" type="{http://www.ftn.uns.ac.rs/zahtev_za_sertifikat}osoba"/&gt;
 *         &lt;element name="razlog_za_podnosenje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mesto_podnosenja_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datum_podnosenja_zahteva" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilacZahteva",
    "razlogZaPodnosenje",
    "mestoPodnosenjaZahteva",
    "datumPodnosenjaZahteva"
})
@XmlRootElement(name = "zahtev_za_izdavanje_sertifikata")
public class ZahtevZaIzdavanjeSertifikata {

    @XmlElement(name = "podnosilac_zahteva", required = true)
    protected Osoba podnosilacZahteva;
    @XmlElement(name = "razlog_za_podnosenje", required = true)
    protected String razlogZaPodnosenje;
    @XmlElement(name = "mesto_podnosenja_zahteva", required = true)
    protected String mestoPodnosenjaZahteva;
    @XmlElement(name = "datum_podnosenja_zahteva", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenjaZahteva;

    /**
     * Gets the value of the podnosilacZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link Osoba }
     *     
     */
    public Osoba getPodnosilacZahteva() {
        return podnosilacZahteva;
    }

    /**
     * Sets the value of the podnosilacZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Osoba }
     *     
     */
    public void setPodnosilacZahteva(Osoba value) {
        this.podnosilacZahteva = value;
    }

    /**
     * Gets the value of the razlogZaPodnosenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazlogZaPodnosenje() {
        return razlogZaPodnosenje;
    }

    /**
     * Sets the value of the razlogZaPodnosenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazlogZaPodnosenje(String value) {
        this.razlogZaPodnosenje = value;
    }

    /**
     * Gets the value of the mestoPodnosenjaZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMestoPodnosenjaZahteva() {
        return mestoPodnosenjaZahteva;
    }

    /**
     * Sets the value of the mestoPodnosenjaZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMestoPodnosenjaZahteva(String value) {
        this.mestoPodnosenjaZahteva = value;
    }

    /**
     * Gets the value of the datumPodnosenjaZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodnosenjaZahteva() {
        return datumPodnosenjaZahteva;
    }

    /**
     * Sets the value of the datumPodnosenjaZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodnosenjaZahteva(XMLGregorianCalendar value) {
        this.datumPodnosenjaZahteva = value;
    }

}
