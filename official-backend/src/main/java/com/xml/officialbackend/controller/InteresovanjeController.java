package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/interesovanje", produces = {"application/xml"})
public class InteresovanjeController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/metadata/{id}")
    public ResponseEntity<?> getMetadata(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/metadata/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/metadata-json/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id, @RequestHeader("Authorization") String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/{id}",
                        HttpMethod.GET,  httpEntity,String.class, id);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

    @GetMapping("/referenced/{documentId}")
    public ResponseEntity<?> findWhereIsReferenced(@PathVariable String documentId, @RequestHeader("Authorization") String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =

                restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/referenced/{documentId}",
                        HttpMethod.GET,  httpEntity,String.class, documentId);
        return new ResponseEntity<>(responseEntity.getBody().split("/")[responseEntity.getBody().split("/").length-1], HttpStatus.OK);
    }
}
