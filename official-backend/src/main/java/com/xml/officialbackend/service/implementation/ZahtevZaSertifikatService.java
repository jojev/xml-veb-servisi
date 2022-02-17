package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.RazlogDTO;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.EmailService;
import main.java.com.xml.officialbackend.service.contract.IDigitalniSertifikatService;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.contract.IZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevZaSertifikatService implements IZahtevZaSertifikatService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private EmailService emailService;

    private IObrazacZaSprovodjenjeImunizacijeService obrazacService;

    private IDigitalniSertifikatService digitalniSertifikatService;

    private IPotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ZahtevZaSertifikatService(BaseRepository baseRepository, JaxBParser jaxBParser,
                                     ExistDbManager existDbManager, MetadataExtractor metadataExtractor,
                                     EmailService emailService, ObrazacZaSprovodjenjeImunizacijeService obrazacService,
                                     IDigitalniSertifikatService digitalniSertifikatService,
                                     IPotvrdaOVakcinacijiService potvrdaOVakcinacijiService) {
        this.baseRepository = baseRepository;
        this.jaxBParser = jaxBParser;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.emailService = emailService;
        this.obrazacService = obrazacService;
        this.digitalniSertifikatService = digitalniSertifikatService;
        this.potvrdaOVakcinacijiService = potvrdaOVakcinacijiService;
    }

    @Override
    public List<ZahtevZaIzdavanjeSertifikata> findAll() {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata findById(String id) throws Exception {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata entity) throws Exception {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata update(ZahtevZaIzdavanjeSertifikata entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void response(RazlogDTO razlogDTO, ZahtevZaIzdavanjeSertifikata zahtev, String accessToken) throws Exception {
        ObrazacZaSprovodjenjeImunizacije obrazac = obrazacService.findByJMBG(accessToken, zahtev.getPodnosilacZahteva().getJmbg().getValue());
        ArrayList<PotvrdaOVakcinaciji> potvrde = potvrdaOVakcinacijiService.findPotvrdeByJMBG(zahtev.getPodnosilacZahteva().getJmbg().getValue());
        if (!razlogDTO.getOdobren()) {
            emailService.sendResponse(obrazac.getPodaciKojeJePopunioPacijent().getLicniPodaci().getImejl(), " ", " ", razlogDTO.getRazlog());
        } else {
            digitalniSertifikatService.send(zahtev, obrazac, potvrde, accessToken);

        }
    }
}
