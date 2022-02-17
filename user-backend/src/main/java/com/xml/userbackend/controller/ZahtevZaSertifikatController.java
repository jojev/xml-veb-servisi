package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import java.io.IOException;
import java.text.ParseException;


import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import main.java.com.xml.userbackend.responses.CountResponse;

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
    public ResponseEntity<CountResponse> findNumberOfZahteva(@RequestParam String startDate, @RequestParam String endDate) throws IOException, ParseException {
		return new ResponseEntity<>(new CountResponse(zahtevZaSertifikatService.getNumberOfRequestForDigitalSertificate(startDate, endDate)), HttpStatus.OK);
    }

    /*
    @PostMapping("/search_by_jmbg")
  //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<?> searchByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchByJMBG(searchDTO);
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    } */

    @PostMapping("/odgovor")
    public ResponseEntity<?> getZahtev(@RequestBody RazlogDTO razlogDTO) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = zahtevZaSertifikatService.setOdgovor(razlogDTO);
        return new ResponseEntity<>(zahtevZaIzdavanjeSertifikata, HttpStatus.OK);
    }

    @PostMapping("/search_by_metadata")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchByMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchMetadata(metadataSearchDTO);
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/search_by_text")
    public ResponseEntity<?> searchByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchByText(searchDTO.getSearch());
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getPendingZahtevi() throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.findPendingZahtevi();
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
