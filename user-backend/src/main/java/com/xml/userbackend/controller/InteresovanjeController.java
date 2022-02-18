package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeList;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;

import java.util.ArrayList;
import java.util.List;

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
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.CREATED);
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

    @GetMapping("/metadata/{id}")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(interesovanjeService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }

    @GetMapping("/search_logical")
    public ResponseEntity<?> searchLogical(@RequestParam(name="search") String search) throws Exception {
        ArrayList<InteresovanjeZaVakcinisanje> interesovanja = interesovanjeService.searchMetadataLogical(URLDecoder.decode(search, "UTF-8"));
        InteresovanjeList list = new InteresovanjeList(interesovanja);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
