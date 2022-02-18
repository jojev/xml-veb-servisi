//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.18 at 02:11:40 AM CET 
//


package main.java.com.xml.userbackend.model.lista_cekanja;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element name="stavka"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="period_cekanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="jmbg_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="email_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="tip_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="doza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "stavka"
})
@XmlRootElement(name = "lista_cekanja")
public class ListaCekanja {

    protected List<ListaCekanja.Stavka> stavka;

    /**
     * Gets the value of the stavka property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stavka property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStavka().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListaCekanja.Stavka }
     *
     *
     */
    public List<ListaCekanja.Stavka> getStavka() {
        if (stavka == null) {
            stavka = new ArrayList<ListaCekanja.Stavka>();
        }
        return this.stavka;
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
     *         &lt;element name="period_cekanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="jmbg_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="email_pacijenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="tip_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="doza" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "stavka")
    @XmlType(name = "", propOrder = {
        "periodCekanja",
        "jmbgPacijenta",
        "emailPacijenta",
        "tipVakcine",
        "doza"
    })
    public static class Stavka {

        @XmlElement(name = "period_cekanja", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar periodCekanja;
        @XmlElement(name = "jmbg_pacijenta", required = true)
        protected String jmbgPacijenta;
        @XmlElement(name = "email_pacijenta", required = true)
        protected String emailPacijenta;
        @XmlElement(name = "tip_vakcine", required = true)
        protected String tipVakcine;
        protected int doza;

        /**
         * Gets the value of the periodCekanja property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getPeriodCekanja() {
            return periodCekanja;
        }

        /**
         * Sets the value of the periodCekanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setPeriodCekanja(XMLGregorianCalendar value) {
            this.periodCekanja = value;
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

    }

}
