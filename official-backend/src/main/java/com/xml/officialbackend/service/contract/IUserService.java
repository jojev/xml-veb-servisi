package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;

public interface IUserService extends IService<Korisnik> {
    RDFNode getUserWithUsername(String username) throws IOException;
}
