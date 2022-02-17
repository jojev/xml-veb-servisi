//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.16 at 05:35:04 PM CET 
//


package main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="vakcina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datum_davanja_vakcine"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                 &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nacin_davanja_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ekstremitet"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="DR"/&gt;
 *               &lt;enumeration value="LR"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="serija_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="proizvodjac" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nezeljena_reakcija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doza", propOrder = {
    "vakcina",
    "datumDavanjaVakcine",
    "nacinDavanjaVakcine",
    "ekstremitet",
    "serijaVakcine",
    "proizvodjac",
    "nezeljenaReakcija"
})
public class Doza {

    @XmlElement(required = true)
    protected String vakcina;
    @XmlElement(name = "datum_davanja_vakcine", required = true)
    protected Doza.DatumDavanjaVakcine datumDavanjaVakcine;
    @XmlElement(name = "nacin_davanja_vakcine", required = true)
    protected String nacinDavanjaVakcine;
    @XmlElement(required = true)
    protected String ekstremitet;
    @XmlElement(name = "serija_vakcine", required = true)
    protected String serijaVakcine;
    @XmlElement(required = true)
    protected String proizvodjac;
    @XmlElement(name = "nezeljena_reakcija", required = true)
    protected String nezeljenaReakcija;

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
     * Gets the value of the datumDavanjaVakcine property.
     *
     * @return
     *     possible object is
     *     {@link Doza.DatumDavanjaVakcine }
     *
     */
    public Doza.DatumDavanjaVakcine getDatumDavanjaVakcine() {
        return datumDavanjaVakcine;
    }

    /**
     * Sets the value of the datumDavanjaVakcine property.
     *
     * @param value
     *     allowed object is
     *     {@link Doza.DatumDavanjaVakcine }
     *
     */
    public void setDatumDavanjaVakcine(Doza.DatumDavanjaVakcine value) {
        this.datumDavanjaVakcine = value;
    }

    /**
     * Gets the value of the nacinDavanjaVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacinDavanjaVakcine() {
        return nacinDavanjaVakcine;
    }

    /**
     * Sets the value of the nacinDavanjaVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacinDavanjaVakcine(String value) {
        this.nacinDavanjaVakcine = value;
    }

    /**
     * Gets the value of the ekstremitet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkstremitet() {
        return ekstremitet;
    }

    /**
     * Sets the value of the ekstremitet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkstremitet(String value) {
        this.ekstremitet = value;
    }

    /**
     * Gets the value of the serijaVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerijaVakcine() {
        return serijaVakcine;
    }

    /**
     * Sets the value of the serijaVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerijaVakcine(String value) {
        this.serijaVakcine = value;
    }

    /**
     * Gets the value of the proizvodjac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProizvodjac() {
        return proizvodjac;
    }

    /**
     * Sets the value of the proizvodjac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProizvodjac(String value) {
        this.proizvodjac = value;
    }

    /**
     * Gets the value of the nezeljenaReakcija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNezeljenaReakcija() {
        return nezeljenaReakcija;
    }

    /**
     * Sets the value of the nezeljenaReakcija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNezeljenaReakcija(String value) {
        this.nezeljenaReakcija = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *       &lt;attribute name="datatype" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class DatumDavanjaVakcine {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }

}
