package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.SearchDTO;
import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObrazacZaSprovodjenjeImunizacijeService implements IObrazacZaSprovodjenjeImunizacijeService {
    private BaseRepository baseRepository;

    private JaxBParser jaxBParser;

    private ExistDbManager existDbManager;

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

//    @Override
//    public List<ObrazacZaSprovodjenjeImunizacije> findByJMBG(String token, String jmbg) {
//        headers.setContentType(MediaType.APPLICATION_XML);
//        //headers.add("Authorization", token);
//        HttpEntity<String> request = new HttpEntity<>(String.format(
//                "<searchdto><search>%s</search></searchdto>",
//                jmbg), headers);
//        ResponseEntity<ObrazacList> response;
//        try {
//            response = restTemplate.exchange("http://localhost:8080/api/v1/preview/obrazac/search_jmbg", HttpMethod.POST, request, ObrazacList.class);
//        } catch (Exception e) {
//            throw new MissingEntityException("Ne postoje saglasnosti sa unetim jmbg.");
//        }
//        return response.getBody().getItems();
//
//    }
    @Override
    public ObrazacZaSprovodjenjeImunizacije findByJMBG(String accessToken, String jmbg) {
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.add("Authorization", accessToken);
        HttpEntity<String> request = new HttpEntity<>(String.format(
                "<searchdto><search>%s</search></searchdto>",
                jmbg), headers);
        ResponseEntity<ObrazacZaSprovodjenjeImunizacije> response;
        try {
            response = restTemplate.exchange("http://localhost:8080/api/v1/saglasnost/find_one_jmbg", HttpMethod.POST, request, ObrazacZaSprovodjenjeImunizacije.class);
        } catch (Exception e) {
            throw new MissingEntityException("Ne postoje saglasnosti sa unetim jmbg.");
        }
        return response.getBody();

    }



    
    @Override
    public ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                                   PodaciKojeJePopunioZdravstveniRadnik podaci,String token) throws Exception {
        String content = jaxBParser.marshallWithoutSchema(podaci).toString();
        content = content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<>(content, headers);
        ResponseEntity<ObrazacZaSprovodjenjeImunizacije> response;
        try {
            response = restTemplate.exchange(String.format("http://localhost:8080/api/v1/saglasnost/%s", jmbg), HttpMethod.PUT, request, ObrazacZaSprovodjenjeImunizacije.class);
        } catch (Exception e) {
            throw new MissingEntityException("Ne postoji saglasnost za unijetog korisnika.");
        }
        return response.getBody();
    }

    @Override
    public String findWhereIsReferenced(String documentId) {
        String sparqlCondition = "?person ?predicate\"" + documentId + "\" .";

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/potvrda_o_vakcinaciji", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            while(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                return rdfNode.toString();
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

}

