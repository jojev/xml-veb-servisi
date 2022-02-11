package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.service.implementation.UserService;
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
    private UserService userService;

    @PostMapping("")
    private ResponseEntity<?> createUser(@RequestBody Korisnik user) throws Exception {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }
}
