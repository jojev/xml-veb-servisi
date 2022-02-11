package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.exception.ConflictException;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IUserService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.xmldb.api.modules.XMLResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    @Autowired
    public UserService(BaseRepository baseRepository, ExistDbManager existDbManager, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
    }

    @Override
    public List<Korisnik> findAll() {
        return null;
    }

    @Override
    public Korisnik findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Korisnik create(Korisnik entity) throws Exception {
        RDFNode user = getUserWithUsername(entity.getKorisnickoIme().getValue());

        if (user != null) {
            throw new ConflictException("Korisničko ime je zauzeto.");
        }

        String userId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/korisnici/" + userId);
        entity.setTypeof("pred:IdentifikatorDokumenta");
        entity.getKorisnickoIme().setDatatype("xs:string");
        entity.getKorisnickoIme().setProperty("pred:Ima");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/korisnici/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        baseRepository.save("/db/korisnik", userId, entity, Korisnik.class);

        XMLResource resource = existDbManager.load("/db/korisnik", userId);
        System.out.println(resource.getContent().toString());
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "korisnici");

        return entity;
    }

    @Override
    public Korisnik update(Korisnik entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public RDFNode getUserWithUsername(String username) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/korisnici/predicate/Ima> \"" + username + "\" .";

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/korisnici", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();

            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                return rdfNode;
            }

            return null;
        }
    }
}
