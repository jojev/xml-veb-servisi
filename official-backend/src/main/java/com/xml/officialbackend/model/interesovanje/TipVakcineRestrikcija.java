//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.10 at 09:32:51 PM CET 
//


package main.java.com.xml.officialbackend.model.interesovanje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tip_vakcine_restrikcija.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tip_vakcine_restrikcija"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pfizer-BioNTech"/&gt;
 *     &lt;enumeration value="Sputnik V"/&gt;
 *     &lt;enumeration value="Sinopharm"/&gt;
 *     &lt;enumeration value="AstraZeneca"/&gt;
 *     &lt;enumeration value="Moderna"/&gt;
 *     &lt;enumeration value="Bilo koja"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tip_vakcine_restrikcija")
@XmlEnum
public enum TipVakcineRestrikcija {

    @XmlEnumValue("Pfizer-BioNTech")
    PFIZER_BIO_N_TECH("Pfizer-BioNTech"),
    @XmlEnumValue("Sputnik V")
    SPUTNIK_V("Sputnik V"),
    @XmlEnumValue("Sinopharm")
    SINOPHARM("Sinopharm"),
    @XmlEnumValue("AstraZeneca")
    ASTRA_ZENECA("AstraZeneca"),
    @XmlEnumValue("Moderna")
    MODERNA("Moderna"),
    @XmlEnumValue("Bilo koja")
    BILO_KOJA("Bilo koja");
    private final String value;

    TipVakcineRestrikcija(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipVakcineRestrikcija fromValue(String v) {
        for (TipVakcineRestrikcija c: TipVakcineRestrikcija.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        return value ;
    }
}
