package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.EmailService;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import main.java.com.xml.officialbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.Doza;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.ObrazacZaSprovodjenjeImunizacije;
import main.java.com.xml.officialbackend.model.obrazac_za_sprovodjenje_imunizacije.PodaciKojeJePopunioZdravstveniRadnik;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IObrazacZaSprovodjenjeImunizacijeService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class ObrazacZaSprovodjenjeImunizacijeService implements IObrazacZaSprovodjenjeImunizacijeService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private EmailService emailService;

    private JaxBParser jaxBParser;

    private final RestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();



    @Autowired
    public ObrazacZaSprovodjenjeImunizacijeService(BaseRepository baseRepository, ExistDbManager existDbManager,
                                                   JaxBParser jaxBParser,RestTemplate restTemplate, EmailService emailService) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.jaxBParser = jaxBParser;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    @Override
    public List<ObrazacZaSprovodjenjeImunizacije> findAll() {
        return null;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije findById(String id) throws Exception {
        return null;
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

    public ArrayList<RDFNode> searchRDF(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/obrazac_za_sprovodjenje_imunizacije/predicate/Kreirao> \"" + jmbg + "\"^^<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral> ;";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/obrazac_za_sprovodjenje_imunizacije", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            if (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
               nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }



   @Override
    public ArrayList<ObrazacZaSprovodjenjeImunizacije> findByJMBG(String jmbg) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(jmbg);
        ArrayList<ObrazacZaSprovodjenjeImunizacije> list = new ArrayList<>();
        for(RDFNode node: nodes){
            String[] parts = node.toString().split("/");
            ObrazacZaSprovodjenjeImunizacije obrazac = baseRepository.findById("/db/obrazac_za_sprovodjenje_imunizacije",
                    parts[parts.length - 1], ObrazacZaSprovodjenjeImunizacije.class);
            list.add(obrazac);
        }

        return list;
    }

    @Override
    public ObrazacZaSprovodjenjeImunizacije update(String jmbg,
                                                   PodaciKojeJePopunioZdravstveniRadnik podaci) throws Exception {
        String content = jaxBParser.marshallWithoutSchema(podaci).toString();
        content =  content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<String>(content, headers);
        ResponseEntity<ObrazacZaSprovodjenjeImunizacije> response;
        try {
            response = restTemplate.exchange(String.format("http://localhost:8080/api/v1/saglasnost/%s", jmbg), HttpMethod.PUT, request, ObrazacZaSprovodjenjeImunizacije.class);
        }
        catch(Exception e) {
            throw new MissingEntityException("Ne postoji saglasnost za unijetog korisnika.");
        }
        return  response.getBody();
    }

}

