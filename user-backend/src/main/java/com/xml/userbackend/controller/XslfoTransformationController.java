package main.java.com.xml.userbackend.controller;

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

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import main.java.com.xml.userbackend.service.contract.ISearchService;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;

@RestController
@RequestMapping(value = "api/v1/xslfo_transformation", produces = {"application/xml"})
public class XslfoTransformationController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private ISearchService searchService;
	
	@Autowired
	private IZahtevZaSertifikatService zahtevService;
	
	@Autowired
	private ISaglasnostService saglasnostService;
	
	@Autowired
	private IInteresovanjeService interesovanjeService;
	
	@PostMapping("/interesovanje")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] interesovanjeStream = interesovanjeService.generateIntersovanjeToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(interesovanjeStream, HttpStatus.OK);
    }
	
	@PostMapping("/zahtev")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getZahtevTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] zahtevStream = zahtevService.generateZahtevToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(zahtevStream, HttpStatus.OK);
    }
	
	@PostMapping("/saglasnost")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getSaglasnostTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] saglasnostStream = saglasnostService.generateSaglasnostToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(saglasnostStream, HttpStatus.OK);
    }
	
	@PostMapping(value = "/digitalni")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> searchDigitalniSertifikatByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
		HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
	    ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/v1/xslfo_transformation/digitalni", HttpMethod.POST,
	              httpEntity, byte[].class);
		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/potvrda")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
	public ResponseEntity<?> getPotvrdaTransformation(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
      HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
      ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/v1/xslfo_transformation/potvrda", HttpMethod.POST,
              httpEntity, byte[].class);
      return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}
}
