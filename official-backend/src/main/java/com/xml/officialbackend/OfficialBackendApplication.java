package main.java.com.xml.officialbackend;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.service.implementation.ListaCekanjaService;
import main.java.com.xml.officialbackend.service.implementation.PoslednjiTerminService;
import main.java.com.xml.officialbackend.service.implementation.TerminService;
import main.java.com.xml.officialbackend.service.implementation.VaccineStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class OfficialBackendApplication implements CommandLineRunner {

	@Autowired
	private ListaCekanjaService lsc;
	
	@Autowired
	private TerminService ts;
	
	@Autowired
	private PoslednjiTerminService pts;
	
	@Autowired
	private VaccineStatusService vss;

	public static void main(String[] args) {
		SpringApplication.run(OfficialBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		ListaCekanja lcs = new ListaCekanja();
//		ListaCekanja.Stavka stavka = new ListaCekanja.Stavka();
//		stavka.setPacijent("Dragana");
//		stavka.setTipVakcine("Pfizer-BioNTech");
//
//		GregorianCalendar c = new GregorianCalendar(2022,2,31);
//		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
//		stavka.setPeriodCekanja(date2);
//
//		lsc.create(lcs);
//
//		//lsc.addPatientToQueue(stavka);
//		//lsc.addPatientToQueue(stavka);
//		//lsc.addPatientToQueue(stavka);
//		
//		StanjeVakcine sv = new StanjeVakcine();
//		sv.setKolicina(10);
//		sv.setVakcina("Pfizer-BioNTech");
//		
//		//vss.create(sv);
//		
//		PoslednjiTermin pt = new PoslednjiTermin();
//		pt.setBrojTermina(128);
//		pt.setDatum(date2);
//		
//		//pts.create(pt);
//		
//		ts.assignToPatient();
	}
}
