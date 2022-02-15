package main.java.com.xml.userbackend.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.responses.CountResponse;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;


@RestController
@RequestMapping(value="/api/v1/interesovanje")
public class InteresovanjeController {

    private IInteresovanjeService interesovanjeService;

    @Autowired
    public InteresovanjeController(IInteresovanjeService interesovanjeService){
        this.interesovanjeService = interesovanjeService;
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje) throws Exception {
        InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje = interesovanjeService.create(intereseovanje);
        return new ResponseEntity<>(interesovanjeZaVakcinisanje, HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<CountResponse> findNumberOfZahteva(String accessToken, @RequestParam String startDate, 
			@RequestParam String endDate) throws IOException {
		return new ResponseEntity<>(new CountResponse(interesovanjeService.getNumberOfInterestedPatients(LocalDate.parse(startDate), LocalDate.parse(endDate))), HttpStatus.OK);
    }

}
