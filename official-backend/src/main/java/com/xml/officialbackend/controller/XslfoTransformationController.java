package main.java.com.xml.officialbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.service.implementation.DigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.implementation.PotvrdaOVakcinacijiService;

@RestController
@RequestMapping(value = "api/v1/xslfo_transformation", produces = {"application/xml"})
public class XslfoTransformationController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PotvrdaOVakcinacijiService potvrdaService;
	
	@Autowired
	private DigitalniSertifikatService sertifikatService;
	
	@PostMapping("/digitalni")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getDigitalniTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] digitalniStream = sertifikatService.generateDigitalniToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(digitalniStream, HttpStatus.OK);
    }
	
	@PostMapping("/potvrda")
	//@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> getInteresovanjeTransformation(@RequestBody SearchDTO searchDTO) throws Exception {
        byte[] potvrdaStream = potvrdaService.generatePotvrdaToPDF(searchDTO.getSearch());
        return new ResponseEntity<>(potvrdaStream, HttpStatus.OK);
    }
}
