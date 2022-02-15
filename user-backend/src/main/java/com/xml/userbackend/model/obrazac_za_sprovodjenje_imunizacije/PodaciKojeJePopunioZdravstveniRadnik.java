package main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "zdravstvenaUstanova",
        "vakcinacijskiPunkt",
        "podaciOLekaru",
        "doze",
        "privremeneKontraindikacije",
        "odlukaKomisije"
})
@XmlRootElement(name = "podaci_koje_je_popunio_zdravstveni_radnik")
public class PodaciKojeJePopunioZdravstveniRadnik {

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

    public PodaciKojeJePopunioZdravstveniRadnik(){

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
