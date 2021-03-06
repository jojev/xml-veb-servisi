package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.dto.RazlogDTO;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/zahtev_za_sertifikat", produces = {"application/xml"})
public class ZahtevZaSertifikatController {

    private RestTemplate restTemplate;
    private IZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    public ZahtevZaSertifikatController(RestTemplate restTemplate, IZahtevZaSertifikatService zahtevZaSertifikatService) {
        this.restTemplate = restTemplate;
        this.zahtevZaSertifikatService = zahtevZaSertifikatService;
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/odgovor")
    public ResponseEntity<?> response(@RequestBody RazlogDTO razlogDTO, @RequestHeader("Authorization") String accessToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(razlogDTO,headers);
        ResponseEntity<ZahtevZaIzdavanjeSertifikata> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/odgovor",
                        HttpMethod.POST,  httpEntity,ZahtevZaIzdavanjeSertifikata.class);
        zahtevZaSertifikatService.response(razlogDTO, responseEntity.getBody(), accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @GetMapping(value = "/pending")
    public ResponseEntity<?> findPendingZahtevi(@RequestHeader("Authorization") String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<ZahtevList> responseEntity =
                restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/pending",
                        HttpMethod.GET, httpEntity, ZahtevList.class);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadata(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/metadata/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/metadata-json/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/referenced/{documentId}")
    public ResponseEntity<?> findWhereIsReferenced(@PathVariable String documentId) throws Exception {
        String zahtev = zahtevZaSertifikatService.findWhereIsReferenced(documentId);
        return new ResponseEntity<>(zahtev.split("/")[zahtev.split("/").length-1], HttpStatus.OK);
    }
}