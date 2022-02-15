package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/zahtev_za_sertifikat")
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
}
