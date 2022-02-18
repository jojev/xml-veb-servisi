package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/saglasnost",produces={"application/xml"})
public class SaglasnostController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IObrazacZaSprovodjenjeImunizacijeService saglasnostService;


    @PutMapping("/{jmbg}")
    @PreAuthorize("hasAnyRole('ROLE_ZDRAVSTVENI_RADNIK')")
    public ResponseEntity<?> updateSaglanost(@RequestBody PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik,
                                             @PathVariable String jmbg,@RequestHeader("Authorization") String accessToken
    ) throws Exception {
        return new ResponseEntity<>(saglasnostService.update(jmbg, podaciKojeJePopunioZdravstveniRadnik,accessToken), HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ZDRAVSTVENI_RADNIK', 'ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadata(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/metadata/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/metadata-json/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }
}