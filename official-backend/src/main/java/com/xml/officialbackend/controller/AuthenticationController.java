package main.java.com.xml.officialbackend.controller;


import main.java.com.xml.officialbackend.config.dto.JwtAuthenticationRequest;
import main.java.com.xml.officialbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.officialbackend.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/auth", produces = {"application/xml"})
public class AuthenticationController {

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserTokenStateDTO userTokenStateDTO = userService.authenticate(authenticationRequest);
        return new ResponseEntity<UserTokenStateDTO>(userTokenStateDTO, HttpStatus.OK);
    }

}
