//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.10 at 09:32:51 PM CET 
//


package main.java.com.xml.officialbackend.model.interesovanje;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for tip_vakcine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tip_vakcine"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.ftn.uns.ac.rs/interesovanje&gt;tip_vakcine_restrikcija"&gt;
 *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tip_vakcine", propOrder = {
    "value"
})
public class TipVakcine {

    @XmlValue
    protected TipVakcineRestrikcija value;
    @XmlAttribute(name = "property", required = true)
    protected String property;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link TipVakcineRestrikcija }
     *     
     */
    public TipVakcineRestrikcija getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipVakcineRestrikcija }
     *     
     */
    public void setValue(TipVakcineRestrikcija value) {
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
