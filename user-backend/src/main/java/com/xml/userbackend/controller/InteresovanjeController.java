package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeList;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;

import java.util.ArrayList;

import main.java.com.xml.userbackend.responses.CountResponse;


@RestController
@RequestMapping(value = "/api/v1/interesovanje", produces = {"application/xml"})
public class InteresovanjeController {

    private IInteresovanjeService interesovanjeService;

    @Autowired
    public InteresovanjeController(IInteresovanjeService interesovanjeService) {
        this.interesovanjeService = interesovanjeService;
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje) throws Exception {
        InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = interesovanjeService.create(intereseovanje);
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<CountResponse> findNumberOfZahteva(String accessToken, @RequestParam String startDate, 
			@RequestParam String endDate) throws IOException, ParseException {
    	System.out.println("U INTERESOVANJU SAMMM");
		return new ResponseEntity<>(new CountResponse(interesovanjeService.getNumberOfInterestedPatients(startDate, endDate)), HttpStatus.OK);
    }

    /*
    @PostMapping("/search_by_jmbg")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanjeZaVakcinisanje = interesovanjeService.searchByJMBG(searchDTO.getSearch());
        InteresovanjeList interesovanjeList = new InteresovanjeList(interesovanjeZaVakcinisanje);
        return new ResponseEntity<>(interesovanjeList, HttpStatus.OK);
    } */


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

}
