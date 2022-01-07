//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.05 at 12:15:08 AM CET 
//


package main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *         &lt;element name="podaci_koje_je_popunio_pacijent"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="licni_podaci" type="{http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije}osoba"/&gt;
 *                   &lt;element name="saglasnost"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="SAGLASAN SAM"/&gt;
 *                         &lt;enumeration value="NISAM SAGLASAN"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="zeljena_vakcina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="podaci_koje_je_popunio_zdravstveni_radnik"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="zdravstvena_ustanova" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="vakcinacijski_punkt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="podaci_o_lekaru" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="doze"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="doza" type="{http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije}doza" maxOccurs="unbounded"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="privremene_kontraindikacije" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="odluka_komisije" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="Da"/&gt;
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
    "podaciKojeJePopunioPacijent",
    "podaciKojeJePopunioZdravstveniRadnik"
})
@XmlRootElement(name = "obrazac_za_sprovodjenje_imunizacije")
public class ObrazacZaSprovodjenjeImunizacije {

    @XmlElement(name = "podaci_koje_je_popunio_pacijent", required = true)
    protected ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioPacijent podaciKojeJePopunioPacijent;
    @XmlElement(name = "podaci_koje_je_popunio_zdravstveni_radnik", required = true)
    protected ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik;

    /**
     * Gets the value of the podaciKojeJePopunioPacijent property.
     * 
     * @return
     *     possible object is
     *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioPacijent }
     *     
     */
    public ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioPacijent getPodaciKojeJePopunioPacijent() {
        return podaciKojeJePopunioPacijent;
    }

    /**
     * Sets the value of the podaciKojeJePopunioPacijent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioPacijent }
     *     
     */
    public void setPodaciKojeJePopunioPacijent(ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioPacijent value) {
        this.podaciKojeJePopunioPacijent = value;
    }

    /**
     * Gets the value of the podaciKojeJePopunioZdravstveniRadnik property.
     * 
     * @return
     *     possible object is
     *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik }
     *     
     */
    public ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik getPodaciKojeJePopunioZdravstveniRadnik() {
        return podaciKojeJePopunioZdravstveniRadnik;
    }

    /**
     * Sets the value of the podaciKojeJePopunioZdravstveniRadnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik }
     *     
     */
    public void setPodaciKojeJePopunioZdravstveniRadnik(ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik value) {
        this.podaciKojeJePopunioZdravstveniRadnik = value;
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
     *         &lt;element name="licni_podaci" type="{http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije}osoba"/&gt;
     *         &lt;element name="saglasnost"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="SAGLASAN SAM"/&gt;
     *               &lt;enumeration value="NISAM SAGLASAN"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="zeljena_vakcina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
        "saglasnost",
        "zeljenaVakcina",
        "datum"
    })
    public static class PodaciKojeJePopunioPacijent {

        @XmlElement(name = "licni_podaci", required = true)
        protected Osoba licniPodaci;
        @XmlElement(required = true)
        protected String saglasnost;
        @XmlElement(name = "zeljena_vakcina", required = true)
        protected String zeljenaVakcina;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datum;

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
         * Gets the value of the saglasnost property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSaglasnost() {
            return saglasnost;
        }

        /**
         * Sets the value of the saglasnost property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSaglasnost(String value) {
            this.saglasnost = value;
        }

        /**
         * Gets the value of the zeljenaVakcina property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZeljenaVakcina() {
            return zeljenaVakcina;
        }

        /**
         * Sets the value of the zeljenaVakcina property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZeljenaVakcina(String value) {
            this.zeljenaVakcina = value;
        }

        /**
         * Gets the value of the datum property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatum() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatum(XMLGregorianCalendar value) {
            this.datum = value;
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
     *         &lt;element name="zdravstvena_ustanova" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="vakcinacijski_punkt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="podaci_o_lekaru" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="doze"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="doza" type="{http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije}doza" maxOccurs="unbounded"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="privremene_kontraindikacije" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="odluka_komisije" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="Da"/&gt;
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
        "zdravstvenaUstanova",
        "vakcinacijskiPunkt",
        "podaciOLekaru",
        "doze",
        "privremeneKontraindikacije",
        "odlukaKomisije"
    })
    public static class PodaciKojeJePopunioZdravstveniRadnik {

        @XmlElement(name = "zdravstvena_ustanova", required = true)
        protected String zdravstvenaUstanova;
        @XmlElement(name = "vakcinacijski_punkt", required = true)
        protected String vakcinacijskiPunkt;
        @XmlElement(name = "podaci_o_lekaru", required = true)
        protected String podaciOLekaru;
        @XmlElement(required = true)
        protected ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik.Doze doze;
        @XmlElement(name = "privremene_kontraindikacije")
        protected String privremeneKontraindikacije;
        @XmlElement(name = "odluka_komisije")
        protected String odlukaKomisije;

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
         * Gets the value of the vakcinacijskiPunkt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVakcinacijskiPunkt() {
            return vakcinacijskiPunkt;
        }

        /**
         * Sets the value of the vakcinacijskiPunkt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVakcinacijskiPunkt(String value) {
            this.vakcinacijskiPunkt = value;
        }

        /**
         * Gets the value of the podaciOLekaru property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPodaciOLekaru() {
            return podaciOLekaru;
        }

        /**
         * Sets the value of the podaciOLekaru property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPodaciOLekaru(String value) {
            this.podaciOLekaru = value;
        }

        /**
         * Gets the value of the doze property.
         * 
         * @return
         *     possible object is
         *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik.Doze }
         *     
         */
        public ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik.Doze getDoze() {
            return doze;
        }

        /**
         * Sets the value of the doze property.
         * 
         * @param value
         *     allowed object is
         *     {@link ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik.Doze }
         *     
         */
        public void setDoze(ObrazacZaSprovodjenjeImunizacije.PodaciKojeJePopunioZdravstveniRadnik.Doze value) {
            this.doze = value;
        }

        /**
         * Gets the value of the privremeneKontraindikacije property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrivremeneKontraindikacije() {
            return privremeneKontraindikacije;
        }

        /**
         * Sets the value of the privremeneKontraindikacije property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrivremeneKontraindikacije(String value) {
            this.privremeneKontraindikacije = value;
        }

        /**
         * Gets the value of the odlukaKomisije property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOdlukaKomisije() {
            return odlukaKomisije;
        }

        /**
         * Sets the value of the odlukaKomisije property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOdlukaKomisije(String value) {
            this.odlukaKomisije = value;
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
         *         &lt;element name="doza" type="{http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije}doza" maxOccurs="unbounded"/&gt;
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
        public static class Doze {

            @XmlElement(required = true)
            protected List<Doza> doza;

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
             * {@link Doza }
             * 
             * 
             */
            public List<Doza> getDoza() {
                if (doza == null) {
                    doza = new ArrayList<Doza>();
                }
                return this.doza;
            }

        }

    }

}