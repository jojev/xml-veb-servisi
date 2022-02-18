package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeList;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.responses.CountResponse;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;


@RestController
@RequestMapping(value = "/api/v1/interesovanje", produces = {"application/xml"})
public class InteresovanjeController {

    private IInteresovanjeService interesovanjeService;

    @Autowired
    public InteresovanjeController(IInteresovanjeService interesovanjeService) {
        this.interesovanjeService = interesovanjeService;
    }



    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN')")
    public ResponseEntity<?> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje) throws Exception {
        InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = interesovanjeService.create(intereseovanje);
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.CREATED);

    }
    @PostMapping(value = "", consumes = {"application/xml"})
	@PreAuthorize("hasAnyRole('ROLE_GRADJANIN')")
    public ResponseEntity<?> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje,  @RequestHeader("Authorization") String accessToken) throws Exception {
    	InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = interesovanjeService.create(intereseovanje, accessToken);
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.OK);
    }
    
    @GetMapping("/count")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<CountResponse> findNumberOfZahteva(String accessToken, @RequestParam String startDate, 
			@RequestParam String endDate) throws IOException, ParseException {
		return new ResponseEntity<>(new CountResponse(interesovanjeService.getNumberOfInterestedPatients(startDate, endDate)), HttpStatus.OK);
    }

    @PostMapping("/search_by_metadata")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchByMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = interesovanjeService.searchMetadata(metadataSearchDTO);
        InteresovanjeList list = new InteresovanjeList(interesovanja);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/search_by_text")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanjeZaVakcinisanje = interesovanjeService.searchByText(searchDTO.getSearch());
        InteresovanjeList interesovanjeList = new InteresovanjeList(interesovanjeZaVakcinisanje);
        return new ResponseEntity<>(interesovanjeList, HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN', 'ROLE_ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }
    
    @PostMapping("/search")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN', 'ROLE_ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> getInteresovanje(@RequestBody SearchDTO searchDTO) throws Exception {
        return new ResponseEntity<>(interesovanjeService.getInteresovanjByJmbg(searchDTO.getSearch()), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }

    @GetMapping("/search_logical")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchLogical(@RequestParam(name="search") String search) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = interesovanjeService.searchMetadataLogical(URLDecoder.decode(search, "UTF-8"));
        InteresovanjeList list = new InteresovanjeList(interesovanja);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
