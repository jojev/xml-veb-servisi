package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PotvrdaOVakcinacijiService implements IPotvrdaOVakcinacijiService {
    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private MetadataExtractor metadataExtractor;

    @Override
    public List<PotvrdaOVakcinaciji> findAll() {
        return null;
    }

    @Override
    public PotvrdaOVakcinaciji findById(String id) throws Exception {
        return null;
    }

    @Override
    public PotvrdaOVakcinaciji create(PotvrdaOVakcinaciji entity) throws Exception {
        String potvrdaOVakcinicijiId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/" + potvrdaOVakcinicijiId);
        entity.setTypeof("pred:IdentifikatorDokumenta");
        entity.getLicniPodaci().getJmbg().setProperty("pred:KreiranZa");
        entity.getLicniPodaci().getJmbg().setDatatype("xs:string");
        entity.getDatumIzdavanjaPotvrde().setProperty("pred:Izdat");
        entity.getDatumIzdavanjaPotvrde().setDatatype("xs:date");
        entity.getQrKod().setProperty("pred:Linkuje");
        entity.getQrKod().setDatatype("xs:string");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        baseRepository.save("/db/potvrda_o_vakcinaciji", potvrdaOVakcinicijiId, entity, PotvrdaOVakcinaciji.class);

        XMLResource resource = existDbManager.load("/db/potvrda_o_vakcinaciji", potvrdaOVakcinicijiId);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "potvrda_o_vakcinaciji");

        return entity;
    }

    @Override
    public PotvrdaOVakcinaciji update(PotvrdaOVakcinaciji entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }
    
    public int getNumberOfVaccinated(LocalDate startDate, LocalDate endDate) throws IOException {
    	String sparqlCondition = "?s <http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/Izdat> ?date. "
				+ "FILTER ( ?date >= \"" + startDate + "\"^^<http://www.w3.org/2001/XMLSchema#date> && ?date < \"" + endDate + "\"^^<http://www.w3.org/2001/XMLSchema#date>)." ;

        try(RDFReadResult result = FusekiReader.readRDFWithSparqlCountQuery("/potvrdaOVakcinaciji", sparqlCondition);) {
            List<String> columnNames = result.getResult().getResultVars();
            
            if(result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                RDFNode rdfNode = row.get(columnName);
                System.out.println(rdfNode.asLiteral().getInt());
                return rdfNode.asLiteral().getInt();
            }
        }
		return 0;
    }
    
}
