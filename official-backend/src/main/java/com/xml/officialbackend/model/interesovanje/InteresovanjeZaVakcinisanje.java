//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.10 at 03:55:58 PM CET 
//


package main.java.com.xml.officialbackend.model.interesovanje;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="licni_podaci" type="{http://www.ftn.uns.ac.rs/interesovanje}osoba"/&gt;
 *         &lt;element name="podaci_o_vakcinisanju" type="{http://www.ftn.uns.ac.rs/interesovanje}vakcinacija"/&gt;
 *         &lt;element name="datum_podnosenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                 &lt;attribute name="Kreiran" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "", propOrder = {
    "licniPodaci",
    "podaciOVakcinisanju",
    "datumPodnosenja"
})
@XmlRootElement(name = "interesovanje_za_vakcinisanje")
public class InteresovanjeZaVakcinisanje {

    @XmlElement(name = "licni_podaci", required = true)
    protected Osoba licniPodaci;
    @XmlElement(name = "podaci_o_vakcinisanju", required = true)
    protected Vakcinacija podaciOVakcinisanju;
    @XmlElement(name = "datum_podnosenja", required = true)
    protected InteresovanjeZaVakcinisanje.DatumPodnosenja datumPodnosenja;

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
     * Gets the value of the podaciOVakcinisanju property.
     *
     * @return
     *     possible object is
     *     {@link Vakcinacija }
     *
     */
    public Vakcinacija getPodaciOVakcinisanju() {
        return podaciOVakcinisanju;
    }

    /**
     * Sets the value of the podaciOVakcinisanju property.
     *
     * @param value
     *     allowed object is
     *     {@link Vakcinacija }
     *
     */
    public void setPodaciOVakcinisanju(Vakcinacija value) {
        this.podaciOVakcinisanju = value;
    }

    /**
     * Gets the value of the datumPodnosenja property.
     *
     * @return
     *     possible object is
     *     {@link InteresovanjeZaVakcinisanje.DatumPodnosenja }
     *
     */
    public InteresovanjeZaVakcinisanje.DatumPodnosenja getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     *
     * @param value
     *     allowed object is
     *     {@link InteresovanjeZaVakcinisanje.DatumPodnosenja }
     *
     */
    public void setDatumPodnosenja(InteresovanjeZaVakcinisanje.DatumPodnosenja value) {
        this.datumPodnosenja = value;
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
     *       &lt;attribute name="Kreiran" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
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
    public static class DatumPodnosenja {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;

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
         * Gets the value of the kreiran property.
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
         * Sets the value of the kreiran property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

    }

}
