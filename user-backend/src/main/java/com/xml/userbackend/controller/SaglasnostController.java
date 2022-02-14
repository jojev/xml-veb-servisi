package main.java.com.xml.userbackend.controller;


import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import main.java.com.xml.userbackend.service.implementation.SaglasnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/saglasnost")
public class SaglasnostController {

    @Autowired
    private ISaglasnostService saglasnostService;


    @PostMapping("")
    public ResponseEntity<?> createSaglasnost(@RequestBody ObrazacZaSprovodjenjeImunizacije saglasnost) throws Exception {
        return new ResponseEntity<>(saglasnostService.create(saglasnost), HttpStatus.CREATED);
    }

    @PutMapping("/{jmbg}")
    public ResponseEntity<?> updateSaglanost(@RequestBody PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik,
                                             @PathVariable String jmbg
                                             ) throws Exception {
        return new ResponseEntity<>(saglasnostService.update(jmbg,podaciKojeJePopunioZdravstveniRadnik), HttpStatus.OK);
    }
}
