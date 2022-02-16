//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.14 at 09:25:51 PM CET 
//


package main.java.com.xml.userbackend.model.digitalni_sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for licni_podaci complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="licni_podaci"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ime_prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="pol"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Musko"/&gt;
 *               &lt;enumeration value="Zensko"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="jmbg" type="{http://www.ftn.uns.ac.rs/digitalni_sertifikat}jmbg"/&gt;
 *         &lt;element name="broj_pasosa" type="{http://www.ftn.uns.ac.rs/digitalni_sertifikat}broj_pasosa"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "licni_podaci", propOrder = {
    "imePrezime",
    "pol",
    "datumRodjenja",
    "jmbg",
    "brojPasosa"
})
public class LicniPodaci {

    @XmlElement(name = "ime_prezime", required = true)
    protected String imePrezime;
    @XmlElement(required = true)
    protected String pol;
    @XmlElement(name = "datum_rodjenja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumRodjenja;
    @XmlElement(required = true)
    protected Jmbg jmbg;
    @XmlElement(name = "broj_pasosa", required = true)
    protected BrojPasosa brojPasosa;

    /**
     * Gets the value of the imePrezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImePrezime() {
        return imePrezime;
    }

    /**
     * Sets the value of the imePrezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImePrezime(String value) {
        this.imePrezime = value;
    }

    /**
     * Gets the value of the pol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPol() {
        return pol;
    }

    /**
     * Sets the value of the pol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPol(String value) {
        this.pol = value;
    }

    /**
     * Gets the value of the datumRodjenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Sets the value of the datumRodjenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumRodjenja(XMLGregorianCalendar value) {
        this.datumRodjenja = value;
    }

    /**
     * Gets the value of the jmbg property.
     * 
     * @return
     *     possible object is
     *     {@link Jmbg }
     *     
     */
    public Jmbg getJmbg() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Jmbg }
     *     
     */
    public void setJmbg(Jmbg value) {
        this.jmbg = value;
    }

    /**
     * Gets the value of the brojPasosa property.
     * 
     * @return
     *     possible object is
     *     {@link BrojPasosa }
     *     
     */
    public BrojPasosa getBrojPasosa() {
        return brojPasosa;
    }

    /**
     * Sets the value of the brojPasosa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrojPasosa }
     *     
     */
    public void setBrojPasosa(BrojPasosa value) {
        this.brojPasosa = value;
    }

}
