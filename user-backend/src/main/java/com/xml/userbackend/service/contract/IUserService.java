package main.java.com.xml.userbackend.service.contract;

import main.java.com.xml.userbackend.config.dto.JwtAuthenticationRequest;
import main.java.com.xml.userbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;


public interface IUserService extends IService<Korisnik> {
    RDFNode getUserWithUsername(String username) throws IOException;

    UserTokenStateDTO authenticate(JwtAuthenticationRequest jwtAuthenticationRequest) throws Exception;
}