package main.java.com.xml.userbackend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import main.java.com.xml.userbackend.service.contract.ISearchService;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.digitalni_sertifikat.DigitalniSertifikatList;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeList;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacList;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinacijiList;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;

@RestController
@RequestMapping(value = "/api/v1/preview", produces = {"application/xml"}, consumes = {"application/xml"})
public class ViewDocumentsController {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
    private ISearchService searchService;
	
	@Autowired
	private IZahtevZaSertifikatService zahtevZaSertifikatService;
	
	@Autowired
	private IInteresovanjeService interesovanjeService;
	
	@Autowired
	private ISaglasnostService saglasnostService;
	
	@PostMapping(value = "/digitalni_sertifikat/search_jmbg")
	  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchDigitalniSertifikatByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<DigitalniSertifikatList> response = restTemplate.exchange("http://localhost:8081/api/v1/search/digitalni_sertifikat/search_jmbg", HttpMethod.POST,
                httpEntity, DigitalniSertifikatList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/potvrda_o_vakcinaciji/search_jmbg")
	  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchPotvrdaOVakcinacijiByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<PotvrdaOVakcinacijiList> response = restTemplate.exchange("http://localhost:8081/api/v1/search/potvrda_o_vakcinaciji/search_jmbg", HttpMethod.POST,
                httpEntity, PotvrdaOVakcinacijiList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/obrazac/search_jmbg")
	  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchObrazacByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
		ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazac = saglasnostService.searchByJMBG(searchDTO);
        ObrazacList list = new ObrazacList(obrazac);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@PostMapping("/zahtev_za_sertifikat/search_jmbg")
  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchZahtevByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchByJMBG(searchDTO);
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	@PostMapping("/interesovanje/search_jmbg")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchInteresovanjeByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanjeZaVakcinisanje = interesovanjeService.searchByJMBG(searchDTO.getSearch());
        InteresovanjeList interesovanjeList = new InteresovanjeList(interesovanjeZaVakcinisanje);
        return new ResponseEntity<>(interesovanjeList, HttpStatus.OK);
    }
}
