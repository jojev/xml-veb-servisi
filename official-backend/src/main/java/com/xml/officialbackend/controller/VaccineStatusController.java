package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcineList;
import main.java.com.xml.officialbackend.service.implementation.TerminService;
import main.java.com.xml.officialbackend.service.implementation.VaccineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/vaccine-status", produces={"application/xml"})
public class VaccineStatusController {
    @Autowired
    private VaccineStatusService vaccineStatusService;
    
    @Autowired
    private TerminService terminService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    private ResponseEntity<?> createVaccineStatus(@RequestBody StanjeVakcine vaccineStatus) throws Exception {
    	StanjeVakcine stanje = vaccineStatusService.create(vaccineStatus);
    	terminService.assignToPatient();
        return new ResponseEntity<>(stanje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    private ResponseEntity<?> updateVaccineStatus(@RequestBody StanjeVakcine vaccineStatus, @PathVariable String id) throws Exception {
    	StanjeVakcine stanje = vaccineStatusService.update(vaccineStatus, id);
    	terminService.assignToPatient();
        return new ResponseEntity<>(stanje, HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    private ResponseEntity<StanjeVakcineList> getAllVaccineStatus() throws Exception {
        System.out.println("DADADDADADADAD");
        List<StanjeVakcine> stanjeVakcineList = vaccineStatusService.findAll();
        StanjeVakcineList list = new StanjeVakcineList(stanjeVakcineList);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
