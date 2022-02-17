package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.config.dto.JwtAuthenticationRequest;
import main.java.com.xml.userbackend.config.dto.UserTokenStateDTO;
import main.java.com.xml.userbackend.exception.BadCredentialException;
import main.java.com.xml.userbackend.exception.ConflictException;
import main.java.com.xml.userbackend.exception.MissingEntityException;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.model.korisnik.Korisnik;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.security.TokenUtils;
import main.java.com.xml.userbackend.service.contract.IUserService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xmldb.api.modules.XMLResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    private PasswordEncoder passwordEncoder;

    private TokenUtils tokenUtils;


    @Autowired
    public UserService(BaseRepository baseRepository, ExistDbManager existDbManager,
                       MetadataExtractor metadataExtractor, PasswordEncoder passwordEncoder,
                       TokenUtils tokenUtils) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtils  =  tokenUtils;
    }

    @Override
    public List<Korisnik> findAll() {
        return null;
    }

    @Override
    public Korisnik findById(String id) throws Exception {
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
        entity.setLozinka(passwordEncoder.encode(entity.getLozinka()));
        baseRepository.save("/db/korisnik", userId, entity, Korisnik.class);

        XMLResource resource = existDbManager.load("/db/korisnik", userId);
        //System.out.println(resource.getContent().toString());
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "korisnici");

        return entity;
    }

    @Override
    public Korisnik update(Korisnik entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public RDFNode getUserWithUsername(String username) throws IOException {
        String sparqlCondition = "?person <http://www.ftn.uns.ac.rs/rdf/korisnici/predicate/Ima> \"" + username + "\" .";

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/korisnici", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            System.out.println(columnNames.get(0));
            System.out.println(columnNames.size());
            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                System.out.println(rdfNode);
                return rdfNode;
            }

            return null;
        }
    }


    @Override
    public UserTokenStateDTO authenticate(JwtAuthenticationRequest jwtAuthenticationRequest) throws Exception {
        RDFNode userId = this.getUserWithUsername(jwtAuthenticationRequest.getUsername());
        if (userId == null) {
            throw new BadCredentialException("Pogrešni kredencijali");
        }
        String[] parts = userId.toString().split("/");
        Korisnik korisnik = baseRepository.findById("/db/korisnik", parts[parts.length-1],Korisnik.class);
        if(!passwordEncoder.matches(jwtAuthenticationRequest.getPassword(),korisnik.getLozinka())){
            throw new BadCredentialException("Pogrešni kredencijali");
        }
//        if(!korisnik.getUloga().get(0).getNaziv().equals("ROLE_GRADJANIN")){
//            throw new BadCredentialException("Logovanje isključivo za građanina");
//        }
        String role = korisnik.getUloga().get(0).getNaziv();
        List<String> roles = new ArrayList<String>();
        roles.add(role);
        String jwt = tokenUtils.generateToken(jwtAuthenticationRequest.getUsername(), role);
        int expiresIn = tokenUtils.getExpiredIn();
        return new UserTokenStateDTO(jwt, new Date().getTime() + expiresIn, roles, jwtAuthenticationRequest.getUsername());
    }
}