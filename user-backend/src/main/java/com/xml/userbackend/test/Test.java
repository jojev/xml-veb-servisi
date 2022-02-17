package main.java.com.xml.userbackend.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.modules.XMLResource;


import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import main.java.com.xml.userbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje.DatumPodnosenja;
import main.java.com.xml.userbackend.model.interesovanje.Jmbg;
import main.java.com.xml.userbackend.model.interesovanje.Osoba;
import main.java.com.xml.userbackend.model.interesovanje.TipVakcine;
import main.java.com.xml.userbackend.model.interesovanje.TipVakcineRestrikcija;
import main.java.com.xml.userbackend.model.interesovanje.Vakcinacija;
import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import main.java.com.xml.userbackend.service.implementation.UserService;

@Component
public class Test {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private IInteresovanjeService interesovanjeService;
    
    @Autowired
    private IZahtevZaSertifikatService zahtevService;
    
    @Autowired
    private ISaglasnostService saglasnostService;

    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private MetadataExtractor metadataExtractor;
    
    @Autowired
    private JaxBParser jaxBParser;
    
    @Autowired
    private UserService userService;

    public void test() throws Exception {
      
    	//createKorisnik();
    	//createInteresovanje();
    	//createZahtev();
    	//createSaglasnost();
    }

    public void createKorisnik() throws IOException, Exception {
    	File file = new File("data/documents/korinik.xml");

        existDbManager.store("/db/korisnicii", "2412998125026.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/korisnicii", "2412998125026.xml");

        Korisnik k = jaxBParser.unmarshall(resource, Korisnik.class);

        userService.create(k);
    }
    
    public void createInteresovanje() throws Exception {
    	InteresovanjeZaVakcinisanje ioi = new InteresovanjeZaVakcinisanje();
    	DatumPodnosenja datum = new DatumPodnosenja();
    	datum.setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-01-20"));
    	ioi.setDatumPodnosenja(datum);
    	
    	Osoba osoba = new Osoba();
    	osoba.setIme("Dragana");
    	osoba.setPrezime("Filipovic");
    	osoba.setDrzavljanstvo("Drzavljanin Republike Srbije");
    	osoba.setAdresaElektronskePoste("filipovic.dada@gmail.com");
    	osoba.setBrojMobilnogTelefona("0655846421");
    	osoba.setBrojFiksnogTelefona("021123999");
    	Jmbg jmgb = new Jmbg();
    	jmgb.setValue("2412998125026");
    	osoba.setJmbg(jmgb);
    	ioi.setLicniPodaci(osoba);
    	
    	Vakcinacija vakcinacije = new Vakcinacija();
    	vakcinacije.setDobrovoljniDavalacKrvi("Ne");
    	vakcinacije.setOpstinaVakcinisanja("Novi Sad");
    	TipVakcine tip = new TipVakcine();
    	tip.setValue(TipVakcineRestrikcija.MODERNA);
    	vakcinacije.setTipVakcine(tip);
    	ioi.setPodaciOVakcinisanju(vakcinacije);
    	
    	interesovanjeService.create(ioi);	
    }
    
    public void createZahtev() throws Exception {
    	File file = new File("data/documents/zahtev_za_izdavanje_sertifikata.xml");

        existDbManager.store("/db/zahtevii", "2412998125026.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/zahtevii", "2412998125026.xml");

        ZahtevZaIzdavanjeSertifikata k = jaxBParser.unmarshall(resource, ZahtevZaIzdavanjeSertifikata.class);

        zahtevService.create(k);
    }
    
    public void createSaglasnost() throws IOException, Exception {
    	File file = new File("data/documents/obrazac_za_sprovodjenje_imunizacije.xml");

        existDbManager.store("/db/saglasnostt", "2412998125026.xml", FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8)));
        XMLResource resource = existDbManager.load("/db/saglasnostt", "2412998125026.xml");

        ObrazacZaSprovodjenjeImunizacije k = jaxBParser.unmarshall(resource, ObrazacZaSprovodjenjeImunizacije.class);

        saglasnostService.create(k);
    }
}
