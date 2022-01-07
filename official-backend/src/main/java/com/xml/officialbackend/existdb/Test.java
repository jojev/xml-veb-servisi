package main.java.com.xml.officialbackend.existdb;

import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;

import java.io.OutputStream;

@Component
public class Test {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;

    String fajl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<interesovanje_za_vakcinisanje\n" +
            "    xmlns=\"http://www.ftn.uns.ac.rs/interesovanje\"\n" +
            "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "    xsi:schemaLocation=\"http://www.ftn.uns.ac.rs/interesovanje" +
            " ./data/interesovanje.xsd\">\n" +
            "    <licni_podaci>\n" +
            "        <drzavljanstvo>Drzavljanin Republike Srbije</drzavljanstvo>\n" +
            "        <jmbg>1234567891234</jmbg>\n" +
            "        <ime>Jovana</ime>\n" +
            "        <prezime>Jevtic</prezime>\n" +
            "        <adresa_elektronske_poste>jovana@gmail.com</adresa_elektronske_poste>\n" +
            "        <broj_mobilnog_telefona>061474749390</broj_mobilnog_telefona>\n" +
            "        <broj_fiksnog_telefona>0214658939</broj_fiksnog_telefona>\n" +
            "    </licni_podaci>\n" +
            "    <podaci_o_vakcinisanju>\n" +
            "        <opstina_vakcinisanja>Novi Sad</opstina_vakcinisanja>\n" +
            "        <tip_vakcine>Pfizer-BioNTech</tip_vakcine>\n" +
            "        <dobrovoljni_davalac_krvi>Da</dobrovoljni_davalac_krvi>\n" +
            "    </podaci_o_vakcinisanju>\n" +
            "    <datum_podnosenja>2021-06-11</datum_podnosenja>\n" +
            "</interesovanje_za_vakcinisanje>";

    public void test() throws Exception {
        existDbManager.store("/db/interesovanje", "2.xml", fajl);
        XMLResource resource = existDbManager.load("/db/interesovanje", "2.xml");

        InteresovanjeZaVakcinisanje interesovanje = jaxBParser.unmarshall(resource, InteresovanjeZaVakcinisanje.class);
        interesovanje.getLicniPodaci().setAdresaElektronskePoste("test@gmail.com");

        OutputStream os = jaxBParser.marshall(InteresovanjeZaVakcinisanje.class, interesovanje);

        existDbManager.store("/db/interesovanje", "2.xml", os.toString());
    }
}
