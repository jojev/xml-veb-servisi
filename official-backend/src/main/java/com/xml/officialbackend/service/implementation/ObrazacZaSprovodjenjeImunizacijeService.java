package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacList;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObrazacZaSprovodjenjeImunizacijeService implements IObrazacZaSprovodjenjeImunizacijeService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private final RestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();


    @Autowired
    public ObrazacZaSprovodjenjeImunizacijeService(BaseRepository baseRepository, JaxBParser jaxBParser, RestTemplate restTemplate) {
        this.baseRepository = baseRepository;
        this.jaxBParser = jaxBParser;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ObrazacZaSprovodjenjeImunizacije> findAll() {
        return null;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije findById(String id) throws Exception {
        return baseRepository.findById("/db/obrazac_za_sprovodjenje_imunizacije", id, ObrazacZaSprovodjenjeImunizacije.class);
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije create(ObrazacZaSprovodjenjeImunizacije entity) throws Exception {
        return null;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(ObrazacZaSprovodjenjeImunizacije entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> findByJMBG(String jmbg) {
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(String.format(
                "<searchdto><search>%s</search></searchdto>",
                jmbg, headers));
        ResponseEntity<ObrazacList> response;
        try {
            response = restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/search_by_jmbg", HttpMethod.POST, request, ObrazacList.class);
        } catch (Exception e) {
            throw new MissingEntityException("Ne postoje saglasnosti sa unetim jmbg.");
        }
        return (ArrayList<ObrazacZaSprovodjenjeImunizacije>) response.getBody().getItems();
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                                   PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception {
        String content = jaxBParser.marshallWithoutSchema(podaci).toString();
        content = content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(content, headers);
        ResponseEntity<ObrazacZaSprovodjenjeImunizacije> response;
        try {
            response = restTemplate.exchange(String.format("http://localhost:8080/api/v1/saglasnost/%s", jmbg), HttpMethod.PUT, request, ObrazacZaSprovodjenjeImunizacije.class);
        } catch (Exception e) {
            throw new MissingEntityException("Ne postoji saglasnost za unijetog korisnika.");
        }
        return response.getBody();
    }

}

