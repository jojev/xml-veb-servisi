//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.05 at 12:22:27 AM CET 
//


package main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji;

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
 *         &lt;element name="sifra_potvrde_vakcinacije"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="\d{7}-\d{7}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="licni_podaci" type="{http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji}osoba"/&gt;
 *         &lt;element name="podaci_o_vakcinaciji" type="{http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji}vakcinacija"/&gt;
 *         &lt;element name="datum_izdavanja_potvrde" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="qr-kod" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "sifraPotvrdeVakcinacije",
    "licniPodaci",
    "podaciOVakcinaciji",
    "datumIzdavanjaPotvrde",
    "qrKod"
})
@XmlRootElement(name = "potvrda_o_vakcinaciji")
public class PotvrdaOVakcinaciji {

    @XmlElement(name = "sifra_potvrde_vakcinacije", required = true)
    protected String sifraPotvrdeVakcinacije;
    @XmlElement(name = "licni_podaci", required = true)
    protected Osoba licniPodaci;
    @XmlElement(name = "podaci_o_vakcinaciji", required = true)
    protected Vakcinacija podaciOVakcinaciji;
    @XmlElement(name = "datum_izdavanja_potvrde", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumIzdavanjaPotvrde;
    @XmlElement(name = "qr-kod", required = true)
    protected String qrKod;

    /**
     * Gets the value of the sifraPotvrdeVakcinacije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraPotvrdeVakcinacije() {
        return sifraPotvrdeVakcinacije;
    }

    /**
     * Sets the value of the sifraPotvrdeVakcinacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraPotvrdeVakcinacije(String value) {
        this.sifraPotvrdeVakcinacije = value;
    }

    /**
     * Gets the value of the licniPodaci property.
     * 
     * @return
     *     possible object is
     *     {@link Osoba }
     *     
     */
    public Osoba getLicniPodaci() {
        return licniPodaci;
    }

    /**
     * Sets the value of the licniPodaci property.
     * 
     * @param value
     *     allowed object is
     *     {@link Osoba }
     *     
     */
    public void setLicniPodaci(Osoba value) {
        this.licniPodaci = value;
    }

    /**
     * Gets the value of the podaciOVakcinaciji property.
     * 
     * @return
     *     possible object is
     *     {@link Vakcinacija }
     *     
     */
    public Vakcinacija getPodaciOVakcinaciji() {
        return podaciOVakcinaciji;
    }

    /**
     * Sets the value of the podaciOVakcinaciji property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vakcinacija }
     *     
     */
    public void setPodaciOVakcinaciji(Vakcinacija value) {
        this.podaciOVakcinaciji = value;
    }

    /**
     * Gets the value of the datumIzdavanjaPotvrde property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIzdavanjaPotvrde() {
        return datumIzdavanjaPotvrde;
    }

    /**
     * Sets the value of the datumIzdavanjaPotvrde property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIzdavanjaPotvrde(XMLGregorianCalendar value) {
        this.datumIzdavanjaPotvrde = value;
    }

    /**
     * Gets the value of the qrKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrKod() {
        return qrKod;
    }

    /**
     * Sets the value of the qrKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrKod(String value) {
        this.qrKod = value;
    }

}