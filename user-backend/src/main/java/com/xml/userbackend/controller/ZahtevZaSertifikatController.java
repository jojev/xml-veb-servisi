package main.java.com.xml.userbackend.controller;


import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.responses.CountResponse;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;

@RestController
@RequestMapping(value = "/api/v1/zahtev_za_sertifikat", produces = {"application/xml"})
public class ZahtevZaSertifikatController {
    private IZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    public ZahtevZaSertifikatController(IZahtevZaSertifikatService zahtevZaSertifikatService) {
        this.zahtevZaSertifikatService = zahtevZaSertifikatService;
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ZahtevZaIzdavanjeSertifikata zahtev) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = zahtevZaSertifikatService.create(zahtev);
        return new ResponseEntity<>(zahtevZaIzdavanjeSertifikata, HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<CountResponse> findNumberOfZahteva(@RequestParam String startDate, @RequestParam String endDate) throws IOException {
		return new ResponseEntity<>(new CountResponse(zahtevZaSertifikatService.getNumberOfRequestForDigitalSertificate(LocalDate.parse(startDate), LocalDate.parse(endDate))), HttpStatus.OK);
    }

    /*
    @PostMapping("/search_by_jmbg")
  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchByJMBG(searchDTO);
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    } */

    @GetMapping("/{documentId}")
    public ResponseEntity<?> getZahtev(@PathVariable String documentId) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = zahtevZaSertifikatService.findById(documentId);
        return new ResponseEntity<>(zahtevZaIzdavanjeSertifikata, HttpStatus.OK);
    }

}
