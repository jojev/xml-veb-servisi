package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinacijiList;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping(value="/api/v1/potvrda-o-vakcinaciji", produces={"application/xml"})
public class PotvrdaOVakcinacijiController {
    @Autowired
    private IPotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', ROLE_ZDRAVSTVENI_RADNIK)")
    private ResponseEntity<?> createPotvrdaOVakcinaciji(@RequestBody PotvrdaOVakcinaciji potvrdaOVakcinaciji, @RequestHeader("Authorization") String accessToken) throws Exception {
        return new ResponseEntity<>(potvrdaOVakcinacijiService.create(potvrdaOVakcinaciji, accessToken), HttpStatus.CREATED);
    }

    @GetMapping(value="/by_jmbg/{jmbg}", produces= MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN', 'ROLE_ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> getPotvrdeByJmbg(@PathVariable String jmbg) throws Exception {
        ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.findPotvrdeByJMBG(jmbg);
        PotvrdaOVakcinacijiList list = new PotvrdaOVakcinacijiList();
        list.setListOfEntityObjects(potvrde);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadataRdf(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(potvrdaOVakcinacijiService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(potvrdaOVakcinacijiService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }
}
