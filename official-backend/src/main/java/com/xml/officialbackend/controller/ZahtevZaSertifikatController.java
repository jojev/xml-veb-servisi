package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.dto.RazlogDTO;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/zahtev_za_sertifikat", produces={"application/xml"})
public class ZahtevZaSertifikatController {

    private RestTemplate restTemplate;
    private IZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    public ZahtevZaSertifikatController(RestTemplate restTemplate, IZahtevZaSertifikatService zahtevZaSertifikatService) {
        this.restTemplate = restTemplate;
        this.zahtevZaSertifikatService = zahtevZaSertifikatService;
    }

    @PostMapping(value = "/odgovor")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> response(@RequestBody RazlogDTO razlogDTO, @RequestHeader("Authorization") String accessToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        //headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<ZahtevZaIzdavanjeSertifikata> responseEntity =
                restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/{documentId}",
                        HttpMethod.GET,  httpEntity,ZahtevZaIzdavanjeSertifikata.class, razlogDTO.getZahtev());
        System.out.println(responseEntity.getBody().getAbout());
        zahtevZaSertifikatService.response(razlogDTO, responseEntity.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}