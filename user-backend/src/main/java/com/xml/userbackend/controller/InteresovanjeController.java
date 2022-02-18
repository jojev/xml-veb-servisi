package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
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


    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje) throws Exception {
        InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = interesovanjeService.create(intereseovanje);
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.CREATED);
    }
    
    @GetMapping("/count")
    public ResponseEntity<CountResponse> findNumberOfZahteva(String accessToken, @RequestParam String startDate, 
			@RequestParam String endDate) throws IOException {
		return new ResponseEntity<>(new CountResponse(interesovanjeService.getNumberOfInterestedPatients(startDate, endDate)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping("/search_by_metadata")
    public ResponseEntity<?> searchByMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = interesovanjeService.searchMetadata(metadataSearchDTO);
        InteresovanjeList list = new InteresovanjeList(interesovanja);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/search_by_text")
    public ResponseEntity<?> searchByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanjeZaVakcinisanje = interesovanjeService.searchByText(searchDTO.getSearch());
        InteresovanjeList interesovanjeList = new InteresovanjeList(interesovanjeZaVakcinisanje);
        return new ResponseEntity<>(interesovanjeList, HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @GetMapping("/search_logical")
    public ResponseEntity<?> searchLogical(@RequestParam(name="search") String search) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = interesovanjeService.searchMetadataLogical(URLDecoder.decode(search, "UTF-8"));
        InteresovanjeList list = new InteresovanjeList(interesovanja);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
