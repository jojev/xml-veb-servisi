package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.dto.MetadataSearchDTO;
import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.rdf.FusekiReader;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IListaCekanjaService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.contract.ITerminService;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    private ITerminService terminService;

    @Autowired
    private IListaCekanjaService listaCekanjaService;

    @Override
    public List<PotvrdaOVakcinaciji> findAll() {
        return null;
    }

    @Override
    public PotvrdaOVakcinaciji findById(String id) throws Exception {
        return baseRepository.findById("/db/potvrda_o_vakcinaciji", id, PotvrdaOVakcinaciji.class);
    }

    @Override
    public PotvrdaOVakcinaciji create(PotvrdaOVakcinaciji entity) throws Exception {
        String potvrdaOVakcinicijiId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/potvrdaOVakcinaciji/" + potvrdaOVakcinicijiId);
        entity.setTypeof("pred:IdentifikatorDokumenta");
        entity.getLicniPodaci().getJmbg().setProperty("pred:KreiranZa");
        entity.getLicniPodaci().getJmbg().setDatatype("xs:string");
        entity.getDatumIzdavanjaPotvrde().setProperty("pred:Izdat");
        entity.getDatumIzdavanjaPotvrde().setDatatype("xs:date");
        entity.getQrKod().setProperty("pred:Linkuje");
        entity.getQrKod().setDatatype("xs:string");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/potvrdaOVakcinaciji/predicate/");
        entity.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        baseRepository.save("/db/potvrdaOVakcinaciji", potvrdaOVakcinicijiId, entity, PotvrdaOVakcinaciji.class);

        XMLResource resource = existDbManager.load("/db/potvrdaOVakcinaciji", potvrdaOVakcinicijiId);
        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "potvrdaOVakcinaciji");

        int numberOfVaccine = entity.getPodaciOVakcinaciji().getDoze().getDoza().size();

        terminService.addTerminOrAddToListaCekanja(entity.getPodaciOVakcinaciji().getNazivVakcine().trim(), numberOfVaccine + 1,
                entity.getLicniPodaci().getJmbg().getValue(), entity.getPodaciOVakcinaciji().getDoze().getDoza().get(numberOfVaccine - 1).getDatumDavanja());

        return entity;
    }

    @Override
    public PotvrdaOVakcinaciji update(PotvrdaOVakcinaciji entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }

    public ArrayList<RDFNode> searchRDF(String jmbg) throws IOException {
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/potvrda_o_vakcinaciji/predicate/KreiranZa> \"" + jmbg + "\" ;";

        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/potvrda_o_vakcinaciji", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));

            }
            return nodes;
        }
    }

    @Override
    public ArrayList<PotvrdaOVakcinaciji> findPotvrdeByJMBG(String jmbg) throws Exception {
        ArrayList<PotvrdaOVakcinaciji> potvrde = new ArrayList<>();
        ArrayList<RDFNode> nodes = searchRDF(jmbg);
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            PotvrdaOVakcinaciji potvrda = findById(parts[parts.length - 1]);
            potvrde.add(potvrda);
        }
        return potvrde;
    }

    @Override
    public ArrayList<PotvrdaOVakcinaciji> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception {
        String value = metadataSearchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/potvrda_o_vakcinaciji", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<PotvrdaOVakcinaciji> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            PotvrdaOVakcinaciji potvrdaOVakcinaciji = findById(parts[parts.length - 1]);
            list.add(potvrdaOVakcinaciji);
        }
        return list;
    }
}
