package main.java.com.xml.officialbackend.controller;


import main.java.com.xml.officialbackend.config.dto.JwtAuthenticationRequest;
import main.java.com.xml.officialbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/auth")
public class AuthenticationController {


    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        try {
            UserTokenStateDTO userTokenStateDTO = authenticate(authenticationRequest);
            return new ResponseEntity<UserTokenStateDTO>(userTokenStateDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private UserTokenStateDTO authenticate(JwtAuthenticationRequest authenticationRequest) {
        List<String> roles = new ArrayList<String>();
        Korisnik user = new Korisnik();
        user.getKorisnickoIme().setValue("andrija");
        String jwt = tokenUtils.generateToken(user.getKorisnickoIme().getValue(), "ROLE_KORISNIK", 1);
        int expiresIn = tokenUtils.getExpiredIn();
        return new UserTokenStateDTO(jwt, new Date().getTime() + expiresIn, roles, user.getKorisnickoIme().getValue());
    }
}
