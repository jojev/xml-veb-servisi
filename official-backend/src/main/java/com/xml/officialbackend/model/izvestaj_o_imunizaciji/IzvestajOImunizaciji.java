//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.09 at 03:07:42 PM CET 
//


package main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
 *         &lt;element name="period"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="od"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                           &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="do"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                           &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_interesovanja_o_imunizaciji"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_zahteva_za_digiatlni_sertifikat"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_izdatih_digitalnih_sertifikata"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="broj_primljenih_doza_vakcina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="raspodela_po_dozama"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="doza" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="redni_broj"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="kolicina"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="raspodela_proizvodjaca"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vakcina" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;integer"&gt;
 *                           &lt;attribute name="tip" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datum_izdavanja"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *       &lt;attribute name="typeof" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "period",
    "brojInteresovanjaOImunizaciji",
    "brojZahtevaZaDigiatlniSertifikat",
    "brojIzdatihDigitalnihSertifikata",
    "brojPrimljenihDozaVakcina",
    "raspodelaPoDozama",
    "raspodelaProizvodjaca",
    "datumIzdavanja"
})
@XmlRootElement(name = "izvestaj_o_imunizaciji")
public class IzvestajOImunizaciji {

    @XmlElement(required = true)
    protected IzvestajOImunizaciji.Period period;
    @XmlElement(name = "broj_interesovanja_o_imunizaciji", required = true)
    protected BigInteger brojInteresovanjaOImunizaciji;
    @XmlElement(name = "broj_zahteva_za_digiatlni_sertifikat", required = true)
    protected BigInteger brojZahtevaZaDigiatlniSertifikat;
    @XmlElement(name = "broj_izdatih_digitalnih_sertifikata", required = true)
    protected BigInteger brojIzdatihDigitalnihSertifikata;
    @XmlElement(name = "broj_primljenih_doza_vakcina", required = true)
    protected BigInteger brojPrimljenihDozaVakcina;
    @XmlElement(name = "raspodela_po_dozama", required = true)
    protected IzvestajOImunizaciji.RaspodelaPoDozama raspodelaPoDozama;
    @XmlElement(name = "raspodela_proizvodjaca", required = true)
    protected IzvestajOImunizaciji.RaspodelaProizvodjaca raspodelaProizvodjaca;
    @XmlElement(name = "datum_izdavanja", required = true)
    protected IzvestajOImunizaciji.DatumIzdavanja datumIzdavanja;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;
    @XmlAttribute(name = "typeof")
    @XmlSchemaType(name = "anySimpleType")
    protected String typeof;

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link IzvestajOImunizaciji.Period }
     *     
     */
    public IzvestajOImunizaciji.Period getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzvestajOImunizaciji.Period }
     *     
     */
    public void setPeriod(IzvestajOImunizaciji.Period value) {
        this.period = value;
    }

    /**
     * Gets the value of the brojInteresovanjaOImunizaciji property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojInteresovanjaOImunizaciji() {
        return brojInteresovanjaOImunizaciji;
    }

    /**
     * Sets the value of the brojInteresovanjaOImunizaciji property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojInteresovanjaOImunizaciji(BigInteger value) {
        this.brojInteresovanjaOImunizaciji = value;
    }

    /**
     * Gets the value of the brojZahtevaZaDigiatlniSertifikat property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojZahtevaZaDigiatlniSertifikat() {
        return brojZahtevaZaDigiatlniSertifikat;
    }

    /**
     * Sets the value of the brojZahtevaZaDigiatlniSertifikat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojZahtevaZaDigiatlniSertifikat(BigInteger value) {
        this.brojZahtevaZaDigiatlniSertifikat = value;
    }

    /**
     * Gets the value of the brojIzdatihDigitalnihSertifikata property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojIzdatihDigitalnihSertifikata() {
        return brojIzdatihDigitalnihSertifikata;
    }

    /**
     * Sets the value of the brojIzdatihDigitalnihSertifikata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojIzdatihDigitalnihSertifikata(BigInteger value) {
        this.brojIzdatihDigitalnihSertifikata = value;
    }

    /**
     * Gets the value of the brojPrimljenihDozaVakcina property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojPrimljenihDozaVakcina() {
        return brojPrimljenihDozaVakcina;
    }

    /**
     * Sets the value of the brojPrimljenihDozaVakcina property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojPrimljenihDozaVakcina(BigInteger value) {
        this.brojPrimljenihDozaVakcina = value;
    }

    /**
     * Gets the value of the raspodelaPoDozama property.
     * 
     * @return
     *     possible object is
     *     {@link IzvestajOImunizaciji.RaspodelaPoDozama }
     *     
     */
    public IzvestajOImunizaciji.RaspodelaPoDozama getRaspodelaPoDozama() {
        return raspodelaPoDozama;
    }

    /**
     * Sets the value of the raspodelaPoDozama property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzvestajOImunizaciji.RaspodelaPoDozama }
     *     
     */
    public void setRaspodelaPoDozama(IzvestajOImunizaciji.RaspodelaPoDozama value) {
        this.raspodelaPoDozama = value;
    }

    /**
     * Gets the value of the raspodelaProizvodjaca property.
     * 
     * @return
     *     possible object is
     *     {@link IzvestajOImunizaciji.RaspodelaProizvodjaca }
     *     
     */
    public IzvestajOImunizaciji.RaspodelaProizvodjaca getRaspodelaProizvodjaca() {
        return raspodelaProizvodjaca;
    }

    /**
     * Sets the value of the raspodelaProizvodjaca property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzvestajOImunizaciji.RaspodelaProizvodjaca }
     *     
     */
    public void setRaspodelaProizvodjaca(IzvestajOImunizaciji.RaspodelaProizvodjaca value) {
        this.raspodelaProizvodjaca = value;
    }

    /**
     * Gets the value of the datumIzdavanja property.
     * 
     * @return
     *     possible object is
     *     {@link IzvestajOImunizaciji.DatumIzdavanja }
     *     
     */
    public IzvestajOImunizaciji.DatumIzdavanja getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Sets the value of the datumIzdavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzvestajOImunizaciji.DatumIzdavanja }
     *     
     */
    public void setDatumIzdavanja(IzvestajOImunizaciji.DatumIzdavanja value) {
        this.datumIzdavanja = value;
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

    /**
     * Gets the value of the typeof property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeof() {
        return typeof;
    }

    /**
     * Sets the value of the typeof property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeof(String value) {
        this.typeof = value;
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
    public static class DatumIzdavanja {

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

    }


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
     *         &lt;element name="od"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="do"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
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
        "od",
        "_do"
    })
    public static class Period {

        @XmlElement(required = true)
        protected IzvestajOImunizaciji.Period.Od od;
        @XmlElement(name = "do", required = true)
        protected IzvestajOImunizaciji.Period.Do _do;

        /**
         * Gets the value of the od property.
         * 
         * @return
         *     possible object is
         *     {@link IzvestajOImunizaciji.Period.Od }
         *     
         */
        public IzvestajOImunizaciji.Period.Od getOd() {
            return od;
        }

        /**
         * Sets the value of the od property.
         * 
         * @param value
         *     allowed object is
         *     {@link IzvestajOImunizaciji.Period.Od }
         *     
         */
        public void setOd(IzvestajOImunizaciji.Period.Od value) {
            this.od = value;
        }

        /**
         * Gets the value of the do property.
         * 
         * @return
         *     possible object is
         *     {@link IzvestajOImunizaciji.Period.Do }
         *     
         */
        public IzvestajOImunizaciji.Period.Do getDo() {
            return _do;
        }

        /**
         * Sets the value of the do property.
         * 
         * @param value
         *     allowed object is
         *     {@link IzvestajOImunizaciji.Period.Do }
         *     
         */
        public void setDo(IzvestajOImunizaciji.Period.Do value) {
            this._do = value;
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
        public static class Do {

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
        public static class Od {

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

        }

    }


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
     *         &lt;element name="doza" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="redni_broj"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="kolicina"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
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
        "doza"
    })
    public static class RaspodelaPoDozama {

        @XmlElement(required = true)
        protected List<IzvestajOImunizaciji.RaspodelaPoDozama.Doza> doza;

        /**
         * Gets the value of the doza property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the doza property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDoza().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IzvestajOImunizaciji.RaspodelaPoDozama.Doza }
         * 
         * 
         */
        public List<IzvestajOImunizaciji.RaspodelaPoDozama.Doza> getDoza() {
            if (doza == null) {
                doza = new ArrayList<IzvestajOImunizaciji.RaspodelaPoDozama.Doza>();
            }
            return this.doza;
        }


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
         *         &lt;element name="redni_broj"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="kolicina"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
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
        @XmlType(name = "", propOrder = {
            "redniBroj",
            "kolicina"
        })
        public static class Doza {

            @XmlElement(name = "redni_broj", required = true)
            protected BigInteger redniBroj;
            @XmlElement(required = true)
            protected BigInteger kolicina;

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

            /**
             * Gets the value of the kolicina property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getKolicina() {
                return kolicina;
            }

            /**
             * Sets the value of the kolicina property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setKolicina(BigInteger value) {
                this.kolicina = value;
            }

        }

    }


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
     *         &lt;element name="vakcina" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;integer"&gt;
     *                 &lt;attribute name="tip" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
        "vakcina"
    })
    public static class RaspodelaProizvodjaca {

        @XmlElement(required = true)
        protected List<IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina> vakcina;

        /**
         * Gets the value of the vakcina property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the vakcina property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVakcina().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina }
         * 
         * 
         */
        public List<IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina> getVakcina() {
            if (vakcina == null) {
                vakcina = new ArrayList<IzvestajOImunizaciji.RaspodelaProizvodjaca.Vakcina>();
            }
            return this.vakcina;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;integer"&gt;
         *       &lt;attribute name="tip" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
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
        public static class Vakcina {

            @XmlValue
            protected BigInteger value;
            @XmlAttribute(name = "tip")
            protected String tip;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setValue(BigInteger value) {
                this.value = value;
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

    }

}
