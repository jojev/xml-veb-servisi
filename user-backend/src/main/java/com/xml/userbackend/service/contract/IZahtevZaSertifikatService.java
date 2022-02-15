package main.java.com.xml.userbackend.service.contract;

import java.io.IOException;
import java.time.LocalDate;

import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;

public interface IZahtevZaSertifikatService extends IService<ZahtevZaIzdavanjeSertifikata> {

    ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata) throws Exception;

	int getNumberOfRequestForDigitalSertificate(LocalDate startDate, LocalDate endDate) throws IOException;
}
