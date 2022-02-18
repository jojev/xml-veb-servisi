package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.service.implementation.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import main.java.com.xml.officialbackend.service.implementation.DigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.implementation.IzvestajService;
import main.java.com.xml.officialbackend.service.implementation.PotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.dto.SearchDTO;

@RestController
@RequestMapping(value = "api/v1/html_transformation", produces = {"application/xml"})
public class HtmlTransformerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PotvrdaOVakcinacijiService potvrdaService;
	
	@Autowired
	private DigitalniSertifikatService sertifikatService;

    @Autowired
    private SearchService searchService;
	
	@Autowired
	private IzvestajService izvestajService;
	
	@PostMapping("/digitalni")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getDigitalniTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] digitalniStream = sertifikatService.generateDigitalniToXHTML(searchDTO.getSearch());
        return new ResponseEntity<>(digitalniStream, HttpStatus.OK);
    }
	
	@PostMapping("/potvrda")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] potvrdaStream = potvrdaService.generatePotvrdaToXHTML(searchDTO.getSearch());
        return new ResponseEntity<>(potvrdaStream, HttpStatus.OK);
    }
	
	@PostMapping("/izvestaj")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getIzvestajTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] izvestajStream = izvestajService.generateIzvestajToXHTML(searchDTO.getSearch());
        return new ResponseEntity<>(izvestajStream, HttpStatus.OK);
	}


    @PostMapping("/interesovanje")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/api/v1/html_transformation/interesovanje", HttpMethod.POST,
                httpEntity, byte[].class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping("/saglasnost")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getObrazacTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/api/v1/html_transformation/saglasnost", HttpMethod.POST,
                httpEntity, byte[].class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping("/zahtev")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getZhatevTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8080/api/v1/html_transformation/zahtev", HttpMethod.POST,
                httpEntity, byte[].class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

    }
}
