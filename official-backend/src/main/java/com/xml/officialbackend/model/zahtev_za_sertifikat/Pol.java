//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.10 at 06:03:11 PM CET 
//


package main.java.com.xml.officialbackend.model.zahtev_za_sertifikat;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pol.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="pol"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Zenski"/&gt;
 *     &lt;enumeration value="Muski"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "pol")
@XmlEnum
public enum Pol {

    @XmlEnumValue("Zenski")
    ZENSKI("Zenski"),
    @XmlEnumValue("Muski")
    MUSKI("Muski");
    private final String value;

    Pol(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Pol fromValue(String v) {
        for (Pol c: Pol.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
