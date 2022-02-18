package main.java.com.xml.officialbackend.controller;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja.Stavka;
import main.java.com.xml.officialbackend.service.implementation.TerminService;

@RestController
@RequestMapping(value="/api/v1/termini")
public class TerminController {
	@Autowired
	private TerminService terminService;
	
	@GetMapping("/add")
	public ResponseEntity<?> addInteresovanjeTermin(@RequestParam String periodCekanja, @RequestParam String jmbg, @RequestParam String email, @RequestParam String tipVakcine, @RequestParam int doza ) throws Exception {
		XMLGregorianCalendar period = 
	  			  DatatypeFactory.newInstance().newXMLGregorianCalendar(periodCekanja);
		
		Stavka stavka = new Stavka();
		stavka.setDoza(doza);
		stavka.setTipVakcine(tipVakcine);
		stavka.setEmailPacijenta(email);
		stavka.setJmbgPacijenta(jmbg);
		stavka.setPeriodCekanja(period);
		
		terminService.addTerminOrAddToListaCekanja(stavka.getTipVakcine(), stavka.getDoza(), stavka.getJmbgPacijenta(), stavka.getEmailPacijenta(), stavka.getPeriodCekanja());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
