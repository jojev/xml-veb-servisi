package main.java.com.xml.userbackend.service.contract;

import java.time.LocalDate;
import main.java.com.xml.userbackend.dto.SearchDTO;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;
import java.util.ArrayList;

public interface IZahtevZaSertifikatService extends IService<ZahtevZaIzdavanjeSertifikata> {

    ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata) throws Exception;

	  int getNumberOfRequestForDigitalSertificate(LocalDate startDate, LocalDate endDate) throws IOException;

    ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException;

    ArrayList<ZahtevZaIzdavanjeSertifikata> searchByJMBG(SearchDTO searchDTO) throws Exception;

}
