package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;


import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<?> create(@RequestBody ZahtevZaIzdavanjeSertifikata zahtev, @RequestHeader("Authorization") String accessToken) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = zahtevZaSertifikatService.create(zahtev, accessToken);
        return new ResponseEntity<>(zahtevZaIzdavanjeSertifikata, HttpStatus.CREATED);
    }
    
    @GetMapping("/count")
	@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    public ResponseEntity<CountResponse> findNumberOfZahteva(@RequestParam String startDate, @RequestParam String endDate) throws IOException, ParseException {

		return new ResponseEntity<>(new CountResponse(zahtevZaSertifikatService.getNumberOfRequestForDigitalSertificate(startDate, endDate)), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping("/odgovor")
    public ResponseEntity<?> getZahtev(@RequestBody RazlogDTO razlogDTO) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = zahtevZaSertifikatService.setOdgovor(razlogDTO);
        return new ResponseEntity<>(zahtevZaIzdavanjeSertifikata, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping("/search_by_metadata")
    public ResponseEntity<?> searchByMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchMetadata(metadataSearchDTO);
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping("/search_by_text")
    public ResponseEntity<?> searchByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchByText(searchDTO.getSearch());
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingZahtevi() throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.findPendingZahtevi();
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/by-jmbg/{jmbg}")
    public ResponseEntity<?> getLastByJMBG(@RequestBody String jmbg, @RequestHeader("Authorization") String accessToken) throws Exception {
        String zahtevId = zahtevZaSertifikatService.getByJmbg(jmbg);
        return new ResponseEntity<>(zahtevId, HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(zahtevZaSertifikatService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(zahtevZaSertifikatService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<?> getById(@PathVariable String documentId, @RequestHeader("Authorization") String accessToken) throws Exception {
        ZahtevZaIzdavanjeSertifikata zahtev = zahtevZaSertifikatService.findById(documentId);
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @GetMapping("/search_logical")
    public ResponseEntity<?> searchLogical(@RequestParam(name="search") String search) throws Exception {
        ArrayList<ZahtevZaIzdavanjeSertifikata> zahtevi = zahtevZaSertifikatService.searchMetadataLogical(URLDecoder.decode(search, "UTF-8"));
        ZahtevList list = new ZahtevList(zahtevi);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
