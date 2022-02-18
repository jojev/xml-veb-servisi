package main.java.com.xml.officialbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.service.implementation.DigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.implementation.IzvestajService;
import main.java.com.xml.officialbackend.service.implementation.PotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.implementation.SearchService;

@RestController
@RequestMapping(value = "api/v1/xslfo_transformation", produces = {"application/xml"})
public class XslfoTransformationController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PotvrdaOVakcinacijiService potvrdaService;
	
	@Autowired
	private DigitalniSertifikatService sertifikatService;
	
	@Autowired 
	private IzvestajService izvestajService;
	
	@Autowired
	private SearchService searchService;
	
	@PostMapping("/digitalni")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getDigitalniTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] digitalniStream = sertifikatService.generateDigitalniToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(digitalniStream, HttpStatus.OK);
    }
	
	@PostMapping("/potvrda")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] potvrdaStream = potvrdaService.generatePotvrdaToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(potvrdaStream, HttpStatus.OK);
    }
	
	@PostMapping(value = "/izvestaj")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> getPotvrdaTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
		byte[] potvrdaStream = izvestajService.generateIzestajToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(potvrdaStream, HttpStatus.OK);
	}
	
	@PostMapping(value = "/obrazac")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> getObrazacTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
		HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
	    ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/api/v1/xslfo_transformation/obrazac", HttpMethod.POST,
	              httpEntity, byte[].class);
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/interesovanje")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
      HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
      ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/v1/xslfo_transformation/interesovanje", HttpMethod.POST,
              httpEntity, byte[].class);
      return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/zahtev")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> getZahtevTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
      HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
      ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/v1/xslfo_transformation/zahtev", HttpMethod.POST,
              httpEntity, byte[].class);
      return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	
}
