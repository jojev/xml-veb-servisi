//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.10 at 06:50:06 PM CET 
//


package main.java.com.xml.userbackend.model;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="vakcina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pfizer, BioNTech"/&gt;
 *               &lt;enumeration value="Sinopharm"/&gt;
 *               &lt;enumeration value="Sputnik V"/&gt;
 *               &lt;enumeration value="AstraZeneca, Oxford"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zdravstvenaUstanova" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="kolicina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
    "vakcina",
    "zdravstvenaUstanova",
    "kolicina"
})
@XmlRootElement(name = "stanjeVakcine")
public class StanjeVakcine {

    @XmlElement(required = true)
    protected String vakcina;
    @XmlElement(required = true)
    protected String zdravstvenaUstanova;
    protected int kolicina;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;

    /**
     * Gets the value of the vakcina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVakcina() {
        return vakcina;
    }

    /**
     * Sets the value of the vakcina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVakcina(String value) {
        this.vakcina = value;
    }

    /**
     * Gets the value of the zdravstvenaUstanova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    /**
     * Sets the value of the zdravstvenaUstanova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZdravstvenaUstanova(String value) {
        this.zdravstvenaUstanova = value;
    }

    /**
     * Gets the value of the kolicina property.
     * 
     */
    public int getKolicina() {
        return kolicina;
    }

    /**
     * Sets the value of the kolicina property.
     * 
     */
    public void setKolicina(int value) {
        this.kolicina = value;
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
