package main.java.com.xml.officialbackend.controller;

import main.java.com.xml.officialbackend.model.Korisnik;
import main.java.com.xml.officialbackend.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    private ResponseEntity<?> createUser(Korisnik user) {
        return new ResponseEntity<>("SDAds", HttpStatus.CREATED);
    }
}
