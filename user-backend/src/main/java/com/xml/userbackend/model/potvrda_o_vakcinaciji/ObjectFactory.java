//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.10 at 06:03:11 PM CET 
//


package main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.potvrda_o_vakcinaciji package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.potvrda_o_vakcinaciji
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PotvrdaOVakcinaciji }
     *
     */
    public PotvrdaOVakcinaciji createPotvrdaOVakcinaciji() {
        return new PotvrdaOVakcinaciji();
    }

    /**
     * Create an instance of {@link Vakcinacija }
     *
     */
    public Vakcinacija createVakcinacija() {
        return new Vakcinacija();
    }

    /**
     * Create an instance of {@link Osoba }
     *
     */
    public Osoba createOsoba() {
        return new Osoba();
    }

    /**
     * Create an instance of {@link PotvrdaOVakcinaciji.DatumIzdavanjaPotvrde }
     *
     */
    public PotvrdaOVakcinaciji.DatumIzdavanjaPotvrde createPotvrdaOVakcinacijiDatumIzdavanjaPotvrde() {
        return new PotvrdaOVakcinaciji.DatumIzdavanjaPotvrde();
    }

    /**
     * Create an instance of {@link PotvrdaOVakcinaciji.QrKod }
     *
     */
    public PotvrdaOVakcinaciji.QrKod createPotvrdaOVakcinacijiQrKod() {
        return new PotvrdaOVakcinaciji.QrKod();
    }

    /**
     * Create an instance of {@link Doza }
     *
     */
    public Doza createDoza() {
        return new Doza();
    }

    /**
     * Create an instance of {@link Jmbg }
     * 
     */
    public Jmbg createJmbg() {
        return new Jmbg();
    }

    /**
     * Create an instance of {@link Vakcinacija.Doze }
     * 
     */
    public Vakcinacija.Doze createVakcinacijaDoze() {
        return new Vakcinacija.Doze();
    }

}
