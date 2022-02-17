package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value="/api/v1/digitalni-sertifikat", produces={"application/xml"})
public class DigitalniSertifikatController {
    @Autowired
    private IDigitalniSertifikatService digitalniSertifikatService;

    @GetMapping("/metadata/{id}")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(digitalniSertifikatService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(digitalniSertifikatService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }
}
