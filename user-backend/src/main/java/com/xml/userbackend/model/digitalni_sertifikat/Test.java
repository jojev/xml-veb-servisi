//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.04 at 11:47:53 PM CET 
//


package main.java.com.xml.userbackend.model.digitalni_sertifikat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for test complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="test"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vrsta_uzorka" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="proizvodjac_testa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datum_vreme_uzorkovanja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datum_vreme_izdavanja_rezultata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rezultat" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="laboratorija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="tip" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "test", propOrder = {
    "vrstaUzorka",
    "proizvodjacTesta",
    "datumVremeUzorkovanja",
    "datumVremeIzdavanjaRezultata",
    "rezultat",
    "laboratorija"
})
public class Test {

    @XmlElement(name = "vrsta_uzorka", required = true, nillable = true)
    protected String vrstaUzorka;
    @XmlElement(name = "proizvodjac_testa", required = true, nillable = true)
    protected String proizvodjacTesta;
    @XmlElement(name = "datum_vreme_uzorkovanja", required = true, nillable = true)
    protected String datumVremeUzorkovanja;
    @XmlElement(name = "datum_vreme_izdavanja_rezultata", required = true, nillable = true)
    protected String datumVremeIzdavanjaRezultata;
    @XmlElement(required = true, nillable = true)
    protected String rezultat;
    @XmlElement(required = true, nillable = true)
    protected String laboratorija;
    @XmlAttribute(name = "tip")
    protected String tip;

    /**
     * Gets the value of the vrstaUzorka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrstaUzorka() {
        return vrstaUzorka;
    }

    /**
     * Sets the value of the vrstaUzorka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrstaUzorka(String value) {
        this.vrstaUzorka = value;
    }

    /**
     * Gets the value of the proizvodjacTesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProizvodjacTesta() {
        return proizvodjacTesta;
    }

    /**
     * Sets the value of the proizvodjacTesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProizvodjacTesta(String value) {
        this.proizvodjacTesta = value;
    }

    /**
     * Gets the value of the datumVremeUzorkovanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumVremeUzorkovanja() {
        return datumVremeUzorkovanja;
    }

    /**
     * Sets the value of the datumVremeUzorkovanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumVremeUzorkovanja(String value) {
        this.datumVremeUzorkovanja = value;
    }

    /**
     * Gets the value of the datumVremeIzdavanjaRezultata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumVremeIzdavanjaRezultata() {
        return datumVremeIzdavanjaRezultata;
    }

    /**
     * Sets the value of the datumVremeIzdavanjaRezultata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumVremeIzdavanjaRezultata(String value) {
        this.datumVremeIzdavanjaRezultata = value;
    }

    /**
     * Gets the value of the rezultat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRezultat() {
        return rezultat;
    }

    /**
     * Sets the value of the rezultat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRezultat(String value) {
        this.rezultat = value;
    }

    /**
     * Gets the value of the laboratorija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaboratorija() {
        return laboratorija;
    }

    /**
     * Sets the value of the laboratorija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaboratorija(String value) {
        this.laboratorija = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTip(String value) {
        this.tip = value;
    }

}
