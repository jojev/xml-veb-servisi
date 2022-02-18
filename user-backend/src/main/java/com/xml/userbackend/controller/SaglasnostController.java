package main.java.com.xml.userbackend.controller;


import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacList;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.userbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.userbackend.service.contract.ISaglasnostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/saglasnost",produces={"application/xml"})
public class SaglasnostController {

    @Autowired
    private ISaglasnostService saglasnostService;

    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN')")
    @PostMapping("")
    public ResponseEntity<?> createSaglasnost(@RequestBody ObrazacZaSprovodjenjeImunizacije saglasnost) throws Exception {
        return new ResponseEntity<>(saglasnostService.create(saglasnost), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ZDRAVSTVENI_RADNIK')")
    @PutMapping("/{jmbg}")
    public ResponseEntity<?> updateSaglanost(@RequestBody PodaciKojeJePopunioZdravstveniRadnik podaciKojeJePopunioZdravstveniRadnik,
                                             @PathVariable String jmbg
    ) throws Exception {
        return new ResponseEntity<>(saglasnostService.update(jmbg, podaciKojeJePopunioZdravstveniRadnik), HttpStatus.OK);
    }


    @GetMapping("/by-jmbg/{jmbg}")
    public ResponseEntity<?> getByJmbg(@PathVariable String jmbg) throws IOException {
       String[] documentUri = saglasnostService.getSaglasnostIdFromJMBG(jmbg).toString().split("/");
        return new ResponseEntity<>(documentUri[documentUri.length - 1], HttpStatus.OK);
    }
  
    @PostMapping("/search_by_jmbg")
    public ResponseEntity<?> searchByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazac = saglasnostService.searchByJMBG(searchDTO);
        ObrazacList list = new ObrazacList(obrazac);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/search_by_metadata")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchByMetadata(@RequestBody MetadataSearchDTO metadataSearchDTO) throws Exception {
        ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazac = saglasnostService.searchMetadata(metadataSearchDTO);
        ObrazacList list = new ObrazacList(obrazac);
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping("/metadata/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadata(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(saglasnostService.readMetadata(id, "N-TRIPLE"), HttpStatus.OK);
    }

    @GetMapping("/metadata-json/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> getMetadataJson(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(saglasnostService.readMetadata(id, "RDF/JSON"), HttpStatus.OK);

    }
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK', 'ROLE_GRADJANIN')")
    @PostMapping("/search_by_text")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchByText(@RequestBody SearchDTO searchDTO) throws Exception {
        ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazacZaSprovodjenjeImunizacijes = saglasnostService.searchByText(searchDTO);
        ObrazacList obrazacList = new ObrazacList(obrazacZaSprovodjenjeImunizacijes);
        return new ResponseEntity<>(obrazacList, HttpStatus.OK);
    }

  
    @PostMapping("/find_one_jmbg")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> findByJMBG(@RequestBody SearchDTO searchDTO) throws Exception {
        ObrazacZaSprovodjenjeImunizacije obrazac = saglasnostService.searchByJMBG(searchDTO).get(0);
        return new ResponseEntity<>(obrazac, HttpStatus.OK);
    }


    @GetMapping("/search_logical")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<?> searchLogical(@RequestParam(name="search") String search) throws Exception {
        ArrayList<ObrazacZaSprovodjenjeImunizacije> obrazacZaSprovodjenjeImunizacijes = saglasnostService.searchMetadataLogical(URLDecoder.decode(search, "UTF-8"));
        ObrazacList obrazacList = new ObrazacList(obrazacZaSprovodjenjeImunizacijes);
        return new ResponseEntity<>(obrazacList, HttpStatus.OK);
    }
}
