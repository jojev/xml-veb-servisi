package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniSertifikatList;
import main.java.com.xml.officialbackend.model.digitalni_sertifikat.DigitalniZeleniSertifikat;
import main.java.com.xml.officialbackend.model.interesovanje.InteresovanjeList;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacList;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinacijiList;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevList;
import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.contract.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/search", produces = {"application/xml"})
public class SearchController {
    @Autowired
    private ISearchService searchService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IDigitalniSertifikatService digitalniSertifikatService;
    @Autowired
    private IPotvrdaOVakcinacijiService potvrdaOVakcinacijiService;


    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping(value = "/interesovanje/search_jmbg")
    public ResponseEntity<?> searchInteresovanjeByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<InteresovanjeList> response = restTemplate.exchange("http://localhost:8080/api/v1/preview/interesovanje/search_jmbg", HttpMethod.POST,
                httpEntity, InteresovanjeList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping(value = "/zahtev_za_sertifikat/search_jmbg")
    public ResponseEntity<?> searchZahtevByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);

        ResponseEntity<ZahtevList> response = restTemplate.exchange("http://localhost:8080/api/v1/preview/zahtev_za_sertifikat/search_jmbg", HttpMethod.POST,
                httpEntity, ZahtevList.class);

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping(value = "/digitalni_sertifikat/search_jmbg")
    public ResponseEntity<?> searchSertifikatByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<DigitalniZeleniSertifikat> digitalniZeleniSertifikati = digitalniSertifikatService.searchByJMBG(searchDTO);
        DigitalniSertifikatList list = new DigitalniSertifikatList(digitalniZeleniSertifikati);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping(value = "/potvrda_o_vakcinaciji/search_jmbg")
    public ResponseEntity<?> searchPotvrdaByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.findPotvrdeByJMBG(searchDTO.getSearch());
        PotvrdaOVakcinacijiList list = new PotvrdaOVakcinacijiList(potvrde);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping(value = "/obrazac/search_jmbg")
    public ResponseEntity<?> searchObrazacByJMBG(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<ObrazacList> response = restTemplate.exchange("http://localhost:8080/api/v1/preview/obrazac/search_jmbg", HttpMethod.POST,
                httpEntity, ObrazacList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/metadata")
    public ResponseEntity<?> searchMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
        if (metadataSearchDTO.getCollection().equals("digitalni_sertifikat")) {
            ArrayList<DigitalniZeleniSertifikat> digitalniZeleniSertifikati = digitalniSertifikatService.searchMetadata(metadataSearchDTO);
            DigitalniSertifikatList list = new DigitalniSertifikatList(digitalniZeleniSertifikati);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("potvrda_o_vakcinaciji")) {
            ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.searchMetadata(metadataSearchDTO);
            PotvrdaOVakcinacijiList list = new PotvrdaOVakcinacijiList(potvrde);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("obrazac_za_imunizaciju")) {
            HttpEntity<String> httpEntity = searchService.setMetadataEntity(metadataSearchDTO, accessToken);
            ResponseEntity<ObrazacList> response = restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/search_by_metadata", HttpMethod.POST,
                    httpEntity, ObrazacList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("zahtev_za_sertifikat")) {
            HttpEntity<String> httpEntity = searchService.setMetadataEntity(metadataSearchDTO, accessToken);
            ResponseEntity<ZahtevList> response = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/search_by_metadata", HttpMethod.POST,
                    httpEntity, ZahtevList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("interesovanje")) {
            HttpEntity<String> httpEntity = searchService.setMetadataEntity(metadataSearchDTO, accessToken);
            ResponseEntity<InteresovanjeList> response = restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/search_by_metadata", HttpMethod.POST,
                    httpEntity, InteresovanjeList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/interesovanje/search_by_text")
    public ResponseEntity<?> searchInteresovanjeByTekst(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<InteresovanjeList> response = restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/search_by_text", HttpMethod.POST,
                httpEntity, InteresovanjeList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/zahtev_za_sertifikat/search_by_text")
    public ResponseEntity<?> searchZahtevByText(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<ZahtevList> response = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/search_by_text", HttpMethod.POST,
                httpEntity, ZahtevList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/digitalni_sertifikat/search_by_text")
    public ResponseEntity<?> searchSertifikatByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<DigitalniZeleniSertifikat> digitalniZeleniSertifikati = digitalniSertifikatService.searchByText(searchDTO);
        DigitalniSertifikatList list = new DigitalniSertifikatList(digitalniZeleniSertifikati);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/potvrda_o_vakcinaciji/search_by_text")
    public ResponseEntity<?> searchPotvrdaByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.searchByText(searchDTO);
        PotvrdaOVakcinacijiList list = new PotvrdaOVakcinacijiList(potvrde);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/obrazac/search_by_text")
    public ResponseEntity<?> searchObrazacByText(@RequestBody SearchDTO searchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {
        HttpEntity<String> httpEntity = searchService.setEntity(searchDTO, accessToken);
        ResponseEntity<ObrazacList> response = restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/search_by_text", HttpMethod.POST,
                httpEntity, ObrazacList.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    @PostMapping(value = "/logical")
    public ResponseEntity<?> searchMetadataLogical(@RequestBody MetadataSearchDTO metadataSearchDTO, @RequestHeader("Authorization") String accessToken) throws Exception {

        if (metadataSearchDTO.getCollection().equals("digitalni_sertifikat")) {
            ArrayList<DigitalniZeleniSertifikat> digitalniZeleniSertifikati = digitalniSertifikatService.searchMetadataLogical(metadataSearchDTO);
            DigitalniSertifikatList list = new DigitalniSertifikatList(digitalniZeleniSertifikati);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("potvrda_o_vakcinaciji")) {
            ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.searchMetadataLogical(metadataSearchDTO);
            PotvrdaOVakcinacijiList list = new PotvrdaOVakcinacijiList(potvrde);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("obrazac_za_imunizaciju")) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", accessToken);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<ObrazacList> response = restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/search_logical?search=" + URLEncoder.encode(metadataSearchDTO.getSearch()), HttpMethod.GET,
                    httpEntity, ObrazacList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("zahtev_za_sertifikat")) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", accessToken);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<ZahtevList> response = restTemplate.exchange("http://localhost:8080/api/v1/zahtev_za_sertifikat/search_logical?search=" + URLEncoder.encode(metadataSearchDTO.getSearch()), HttpMethod.GET,
                    httpEntity, ZahtevList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } else if (metadataSearchDTO.getCollection().equals("interesovanje")) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", accessToken);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<InteresovanjeList> response = restTemplate.exchange("http://localhost:8080/api/v1/interesovanje/search_logical?search=" + URLEncoder.encode(metadataSearchDTO.getSearch()), HttpMethod.GET,
                    httpEntity, InteresovanjeList.class);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
