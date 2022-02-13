package main.java.com.xml.userbackend.controller;

import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import main.java.com.xml.userbackend.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/user", produces={"application/xml"})
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("")
    private ResponseEntity<?> createUser(@RequestBody Korisnik user) throws Exception {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
}