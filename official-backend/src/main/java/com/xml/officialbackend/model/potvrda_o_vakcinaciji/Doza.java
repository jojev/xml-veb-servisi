//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.13 at 02:37:42 PM CET 
//


package main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


/**
 * <p>Java class for doza complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doza"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datum_davanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="serija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="redni_broj"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *             &lt;minInclusive value="1"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doza", propOrder = {
    "datumDavanja",
    "serija"
})
public class Doza {

    @XmlElement(name = "datum_davanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumDavanja;
    @XmlElement(required = true)
    protected String serija;
    @XmlAttribute(name = "redni_broj")
    protected BigInteger redniBroj;

    /**
     * Gets the value of the datumDavanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumDavanja() {
        return datumDavanja;
    }

    /**
     * Sets the value of the datumDavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDavanja(XMLGregorianCalendar value) {
        this.datumDavanja = value;
    }

    /**
     * Gets the value of the serija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerija() {
        return serija;
    }

    /**
     * Sets the value of the serija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerija(String value) {
        this.serija = value;
    }

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRedniBroj(BigInteger value) {
        this.redniBroj = value;
    }

}
