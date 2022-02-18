package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/saglasnost",produces={"application/xml"})
public class SaglasnostController {

    @Autowired
    private IObrazacZaSprovodjenjeImunizacijeService saglasnostService;


    @PreAuthorize("hasAnyRole('ROLE_ZDRAVSTVENI_RADNIK')")
    @PutMapping("/{jmbg}")
    public ResponseEntity<?> updateSaglanost(@RequestBody PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik,
                                             @PathVariable String jmbg,@RequestHeader("Authorization") String accessToken
    ) throws Exception {
        return new ResponseEntity<>(saglasnostService.update(jmbg, podaciKojeJePopunioZdravstveniRadnik,accessToken), HttpStatus.OK);
    }
}