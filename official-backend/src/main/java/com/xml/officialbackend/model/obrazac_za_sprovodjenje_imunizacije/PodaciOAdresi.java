//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.05 at 12:15:08 AM CET 
//


package main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for podaci_o_adresi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="podaci_o_adresi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ulica_broj" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="naselje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="grad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "podaci_o_adresi", propOrder = {
    "ulicaBroj",
    "naselje",
    "grad"
})
public class PodaciOAdresi {

    @XmlElement(name = "ulica_broj", required = true)
    protected String ulicaBroj;
    @XmlElement(required = true)
    protected String naselje;
    @XmlElement(required = true)
    protected String grad;

    /**
     * Gets the value of the ulicaBroj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUlicaBroj() {
        return ulicaBroj;
    }

    /**
     * Sets the value of the ulicaBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUlicaBroj(String value) {
        this.ulicaBroj = value;
    }

    /**
     * Gets the value of the naselje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaselje() {
        return naselje;
    }

    /**
     * Sets the value of the naselje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaselje(String value) {
        this.naselje = value;
    }

    /**
     * Gets the value of the grad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrad(String value) {
        this.grad = value;
    }

}
