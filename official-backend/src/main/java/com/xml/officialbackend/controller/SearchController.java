package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.service.contract.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/search", produces = {"application/xml"})
public class SearchController {
    private ISearchService searchService;
    private RestTemplate restTemplate;

    @Autowired
    public SearchController(ISearchService searchService, RestTemplate restTemplate) {
        this.searchService = searchService;
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "/interesovanje/search_jmbg")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchInteresovanjeByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<InteresovanjeZaVakcinisanje> response = restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/search_by_jmbg", HttpMethod.POST,
                httpEntity, InteresovanjeZaVakcinisanje.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/zahtev_za_sertifikat/search_jmbg")
    //@PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchZahtevByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<ZahtevZaIzdavanjeSertifikata> response = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/search_by_jmbg", HttpMethod.POST,
                httpEntity, ZahtevZaIzdavanjeSertifikata.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }


}
