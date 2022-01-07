//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.05 at 12:15:08 AM CET 
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
 *                   &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="do" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
 *         &lt;element name="broj_novo_vakcinisanih"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;minInclusive value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
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
 *         &lt;element name="datum_izdavanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
    "period",
    "brojInteresovanjaOImunizaciji",
    "brojZahtevaZaDigiatlniSertifikat",
    "brojIzdatihDigitalnihSertifikata",
    "brojPrimljenihDozaVakcina",
    "brojNovoVakcinisanih",
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
    @XmlElement(name = "broj_novo_vakcinisanih", required = true)
    protected BigInteger brojNovoVakcinisanih;
    @XmlElement(name = "raspodela_proizvodjaca", required = true)
    protected IzvestajOImunizaciji.RaspodelaProizvodjaca raspodelaProizvodjaca;
    @XmlElement(name = "datum_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumIzdavanja;

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
     * Gets the value of the brojNovoVakcinisanih property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojNovoVakcinisanih() {
        return brojNovoVakcinisanih;
    }

    /**
     * Sets the value of the brojNovoVakcinisanih property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojNovoVakcinisanih(BigInteger value) {
        this.brojNovoVakcinisanih = value;
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
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Sets the value of the datumIzdavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIzdavanja(XMLGregorianCalendar value) {
        this.datumIzdavanja = value;
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
     *         &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="do" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar od;
        @XmlElement(name = "do", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar _do;

        /**
         * Gets the value of the od property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getOd() {
            return od;
        }

        /**
         * Sets the value of the od property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setOd(XMLGregorianCalendar value) {
            this.od = value;
        }

        /**
         * Gets the value of the do property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDo() {
            return _do;
        }

        /**
         * Sets the value of the do property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDo(XMLGregorianCalendar value) {
            this._do = value;
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