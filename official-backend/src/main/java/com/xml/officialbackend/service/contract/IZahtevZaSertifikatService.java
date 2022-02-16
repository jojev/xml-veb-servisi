package main.java.com.xml.officialbackend.service.contract;

import main.java.com.xml.officialbackend.dto.RazlogDTO;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;

public interface IZahtevZaSertifikatService extends IService<ZahtevZaIzdavanjeSertifikata>{

    void response(RazlogDTO razlogDTO, ZahtevZaIzdavanjeSertifikata zahtev) throws Exception;
}
