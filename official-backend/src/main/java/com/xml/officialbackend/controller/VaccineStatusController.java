package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.dto.StanjeVakcineList;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.service.implementation.VaccineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/vaccine-status", produces={"application/xml"})
public class VaccineStatusController {
    @Autowired
    private VaccineStatusService vaccineStatusService;

    @PostMapping("")
    private ResponseEntity<?> createVaccineStatus(@RequestBody StanjeVakcine vaccineStatus) throws Exception {
        return new ResponseEntity<>(vaccineStatusService.create(vaccineStatus), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateVaccineStatus(@RequestBody StanjeVakcine vaccineStatus, @PathVariable String id) throws Exception {
        return new ResponseEntity<>(vaccineStatusService.update(vaccineStatus, id), HttpStatus.OK);
    }

    @GetMapping("")
    private ResponseEntity<StanjeVakcineList> getAllVaccineStatus() throws Exception {
        List<StanjeVakcine> stanjeVakcineList = vaccineStatusService.findAll();
        StanjeVakcineList list = new StanjeVakcineList(stanjeVakcineList);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
