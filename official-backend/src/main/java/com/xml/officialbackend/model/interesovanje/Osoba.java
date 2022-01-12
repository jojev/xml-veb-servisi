//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.10 at 09:32:51 PM CET 
//


package main.java.com.xml.officialbackend.model.interesovanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for osoba complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="osoba"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="drzavljanstvo"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Drzavljanin Republike Srbije"/&gt;
 *               &lt;enumeration value="Strani drzavljanin sa boravkom u RS"/&gt;
 *               &lt;enumeration value="Strani drzavljanin bez boravka u RS"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="jmbg" type="{http://www.ftn.uns.ac.rs/interesovanje}jmbg"/&gt;
 *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="adresa_elektronske_poste"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[^@]+@[^.]+..+"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_mobilnog_telefona"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="06\d{8,10}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_fiksnog_telefona"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="0\d{8,9}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "osoba", propOrder = {
    "drzavljanstvo",
    "jmbg",
    "ime",
    "prezime",
    "adresaElektronskePoste",
    "brojMobilnogTelefona",
    "brojFiksnogTelefona"
})
public class Osoba {

    @XmlElement(required = true)
    protected String drzavljanstvo;
    @XmlElement(required = true)
    protected Jmbg jmbg;
    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String prezime;
    @XmlElement(name = "adresa_elektronske_poste", required = true)
    protected String adresaElektronskePoste;
    @XmlElement(name = "broj_mobilnog_telefona", required = true)
    protected String brojMobilnogTelefona;
    @XmlElement(name = "broj_fiksnog_telefona", required = true)
    protected String brojFiksnogTelefona;

    /**
     * Gets the value of the drzavljanstvo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    /**
     * Sets the value of the drzavljanstvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrzavljanstvo(String value) {
        this.drzavljanstvo = value;
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
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the adresaElektronskePoste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresaElektronskePoste() {
        return adresaElektronskePoste;
    }

    /**
     * Sets the value of the adresaElektronskePoste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresaElektronskePoste(String value) {
        this.adresaElektronskePoste = value;
    }

    /**
     * Gets the value of the brojMobilnogTelefona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojMobilnogTelefona() {
        return brojMobilnogTelefona;
    }

    /**
     * Sets the value of the brojMobilnogTelefona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojMobilnogTelefona(String value) {
        this.brojMobilnogTelefona = value;
    }

    /**
     * Gets the value of the brojFiksnogTelefona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojFiksnogTelefona() {
        return brojFiksnogTelefona;
    }

    /**
     * Sets the value of the brojFiksnogTelefona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojFiksnogTelefona(String value) {
        this.brojFiksnogTelefona = value;
    }

}
