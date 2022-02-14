package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.config.dto.JwtAuthenticationRequest;
import main.java.com.xml.officialbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;

public interface IUserService extends IService<Korisnik> {
    RDFNode getUserWithUsername(String username) throws IOException;

    UserTokenStateDTO authenticate(JwtAuthenticationRequest jwtAuthenticationRequest) throws Exception;
}
