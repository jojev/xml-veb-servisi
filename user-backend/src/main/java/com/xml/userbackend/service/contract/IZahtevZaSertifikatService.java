package main.java.com.xml.userbackend.service.contract;

import main.java.com.xml.userbackend.dto.RazlogDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;

public interface IZahtevZaSertifikatService extends IService<ZahtevZaIzdavanjeSertifikata> {

    ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata) throws Exception;

    RDFNode searchRDF(SearchDTO searchDTO) throws IOException;

    ZahtevZaIzdavanjeSertifikata searchByJMBG(SearchDTO searchDTO) throws Exception;

    void response(RazlogDTO razlogDTO) throws Exception;
}
