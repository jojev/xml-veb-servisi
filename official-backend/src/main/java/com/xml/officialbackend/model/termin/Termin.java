//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.18 at 11:51:34 AM CET 
//


package main.java.com.xml.officialbackend.model.termin;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


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
 *         &lt;element name="datum_vreme" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="trajanje" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="jmbg_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="email_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tip_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="doza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ispostovan" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="obradjen" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumVreme",
    "trajanje",
    "jmbgPacijenta",
    "emailPacijenta",
    "tipVakcine",
    "doza",
    "ispostovan",
    "obradjen"
})
@XmlRootElement(name = "termin")
public class Termin {

    @XmlElement(name = "datum_vreme", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumVreme;
    @XmlElement(required = true)
    protected BigInteger trajanje;
    @XmlElement(name = "jmbg_pacijenta", required = true)
    protected String jmbgPacijenta;
    @XmlElement(name = "email_pacijenta", required = true)
    protected String emailPacijenta;
    @XmlElement(name = "tip_vakcine", required = true)
    protected String tipVakcine;
    protected int doza;
    protected boolean ispostovan;
    protected boolean obradjen;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;

    /**
     * Gets the value of the datumVreme property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumVreme() {
        return datumVreme;
    }

    /**
     * Sets the value of the datumVreme property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumVreme(XMLGregorianCalendar value) {
        this.datumVreme = value;
    }

    /**
     * Gets the value of the trajanje property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTrajanje() {
        return trajanje;
    }

    /**
     * Sets the value of the trajanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTrajanje(BigInteger value) {
        this.trajanje = value;
    }

    /**
     * Gets the value of the jmbgPacijenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmbgPacijenta() {
        return jmbgPacijenta;
    }

    /**
     * Sets the value of the jmbgPacijenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmbgPacijenta(String value) {
        this.jmbgPacijenta = value;
    }

    /**
     * Gets the value of the emailPacijenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailPacijenta() {
        return emailPacijenta;
    }

    /**
     * Sets the value of the emailPacijenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailPacijenta(String value) {
        this.emailPacijenta = value;
    }

    /**
     * Gets the value of the tipVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipVakcine() {
        return tipVakcine;
    }

    /**
     * Sets the value of the tipVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipVakcine(String value) {
        this.tipVakcine = value;
    }

    /**
     * Gets the value of the doza property.
     * 
     */
    public int getDoza() {
        return doza;
    }

    /**
     * Sets the value of the doza property.
     * 
     */
    public void setDoza(int value) {
        this.doza = value;
    }

    /**
     * Gets the value of the ispostovan property.
     * 
     */
    public boolean isIspostovan() {
        return ispostovan;
    }

    /**
     * Sets the value of the ispostovan property.
     * 
     */
    public void setIspostovan(boolean value) {
        this.ispostovan = value;
    }

    /**
     * Gets the value of the obradjen property.
     * 
     */
    public boolean isObradjen() {
        return obradjen;
    }

    /**
     * Sets the value of the obradjen property.
     * 
     */
    public void setObradjen(boolean value) {
        this.obradjen = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

}
