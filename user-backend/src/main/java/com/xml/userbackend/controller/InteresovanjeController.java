package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import main.java.com.xml.userbackend.service.implementation.InteresovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/v1/interesovanje")
public class InteresovanjeController {

    private IInteresovanjeService interesovanjeService;

    @Autowired
    public InteresovanjeController(IInteresovanjeService interesovanjeService){
        this.interesovanjeService = interesovanjeService;
    }


    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody InteresovanjeZaVakcinisanje intereseovanje) throws Exception {
//        String documentId = interesovanjeService.create(intereseovanje);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(){
        return new ResponseEntity<>("lalalalal", HttpStatus.OK);
    }
}
