package main.java.com.xml.officialbackend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import main.java.com.xml.officialbackend.model.izvestaj_o_imunizaciji.IzvestajOImunizaciji;
import main.java.com.xml.officialbackend.reponses.CountResponse;
import main.java.com.xml.officialbackend.service.contract.IIzvestajService;

import java.io.IOException;

@RestController
@RequestMapping(value="/api/v1/izvestaj", produces={"application/xml"})
public class IzvestajOVakcinacijiController {

	@Autowired
	private IIzvestajService izvestajService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
	public ResponseEntity<IzvestajOImunizaciji> searchInteresovanjeByJMBG(@RequestHeader("Authorization") String accessToken, @RequestParam String startDate, 
			@RequestParam String endDate) throws Exception {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", accessToken);
	    headers.setContentType(MediaType.APPLICATION_XML);
	    HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
	    
        ResponseEntity<CountResponse> interesovanjeCnt = restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/count?startDate=" + startDate + "&endDate=" + endDate,
        		HttpMethod.GET,httpEntity, CountResponse.class);
        ResponseEntity<CountResponse> zahtevCnt = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/count?startDate=" + startDate + "&endDate=" + endDate, 
        		HttpMethod.GET, httpEntity, CountResponse.class);
      
        IzvestajOImunizaciji izvestaj = izvestajService.createReport(startDate, endDate, interesovanjeCnt.getBody().getValue(), zahtevCnt.getBody().getValue());
        return new ResponseEntity<>(izvestaj, HttpStatus.OK);
    }

	@GetMapping("/metadata/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
	public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
		return new ResponseEntity<>(izvestajService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
	}

	@GetMapping("/metadata-json/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
	public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
		return new ResponseEntity<>(izvestajService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
	}
}
