package main.java.com.xml.userbackend.model.digitalni_sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for digitalni_potpis complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="digitalni_potpis"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="uprava" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "digitalni_potpis", propOrder = {
    "uprava",
    "adresa",
    "datum"
})
public class DigitalniPotpis {

    @XmlElement(required = true)
    protected String uprava;
    @XmlElement(required = true)
    protected String adresa;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the uprava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUprava() {
        return uprava;
    }

    /**
     * Sets the value of the uprava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUprava(String value) {
        this.uprava = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresa(String value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

}
