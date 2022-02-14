package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.dto.RazlogDTO;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/zahtev_za_sertifikat", produces={"application/xml"})
public class ZahtevZaSertifikatController {

    private RestTemplate restTemplate;

    @Autowired
    public ZahtevZaSertifikatController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/odgovor")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> response(@RequestBody RazlogDTO razlogDTO, @RequestHeader("Authorization") String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> httpEntity = new HttpEntity<String>(String.format(
                "<razlogdto><razlog>%s</razlog><odobren>%b</odobren><zahtev>%s</zahtev></razlogdto>",
                razlogDTO.getRazlog(), razlogDTO.getOdobren(), razlogDTO.getZahtev()), headers);
        restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/odgovor", HttpMethod.POST,
                httpEntity, Object.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}