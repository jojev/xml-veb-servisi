package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/potvrda-o-vakcinaciji", produces={"application/xml"})
public class PotvrdaOVakcinacijiController {
    @Autowired
    private IPotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @PostMapping("")
    private ResponseEntity<?> createPotvrdaOVakcinaciji(@RequestBody PotvrdaOVakcinaciji potvrdaOVakcinaciji) throws Exception {
    	System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        return new ResponseEntity<>(potvrdaOVakcinacijiService.create(potvrdaOVakcinaciji), HttpStatus.CREATED);
    }
}
