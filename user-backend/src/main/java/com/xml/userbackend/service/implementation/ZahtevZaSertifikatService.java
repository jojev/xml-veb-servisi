package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.dto.MetadataSearchDTO;
import main.java.com.xml.userbackend.dto.SearchDTO;
import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.model.zahtev_za_sertifikat.ZahtevZaIzdavanjeSertifikata;
import main.java.com.xml.userbackend.rdf.FusekiReader;
import main.java.com.xml.userbackend.rdf.FusekiWriter;
import main.java.com.xml.userbackend.rdf.MetadataExtractor;
import main.java.com.xml.userbackend.rdf.RDFReadResult;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.contract.IZahtevZaSertifikatService;
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
public class ZahtevZaSertifikatService implements IZahtevZaSertifikatService {
    private BaseRepository baseRepository;


    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    @Autowired
    public ZahtevZaSertifikatService(BaseRepository baseRepository, ExistDbManager existDbManager,
                                     MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;

    }

    @Override
    public List<ZahtevZaIzdavanjeSertifikata> findAll() {
        return null;
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata findById(String id) throws Exception {
        return baseRepository.findById("/db/zahtev_za_sertifikat", id, ZahtevZaIzdavanjeSertifikata.class);
    }

    @Override
    public ZahtevZaIzdavanjeSertifikata update(ZahtevZaIzdavanjeSertifikata entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ZahtevZaIzdavanjeSertifikata create(ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata) throws Exception {
        String documentId = UUID.randomUUID().toString();
        zahtevZaIzdavanjeSertifikata.setAbout("http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/" + documentId);
        zahtevZaIzdavanjeSertifikata.setTypeof("pred:IdentifikatorDokumenta");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getJmbg().setProperty("pred:PodneoJmbg");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getJmbg().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getBrojPasosa().setProperty("pred:PodneoBrPasosa");
        zahtevZaIzdavanjeSertifikata.getPodnosilacZahteva().getBrojPasosa().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getDatumPodnosenjaZahteva().setProperty("pred:IzdatDatuma");
        zahtevZaIzdavanjeSertifikata.getDatumPodnosenjaZahteva().setDatatype("xs:string");
        zahtevZaIzdavanjeSertifikata.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/");
        zahtevZaIzdavanjeSertifikata.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        baseRepository.save("db/zahtev_za_sertifikat", documentId, zahtevZaIzdavanjeSertifikata, ZahtevZaIzdavanjeSertifikata.class);

        XMLResource resource = existDbManager.load("/db/zahtev_za_sertifikat", documentId);

        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        FusekiWriter.saveRDF(new ByteArrayInputStream(out), "zahtev_za_sertifikat");
        return zahtevZaIzdavanjeSertifikata;
    }

    @Override
    public ArrayList<RDFNode> searchRDF(SearchDTO searchDTO) throws IOException {
        String jmbg = searchDTO.getSearch();
        String sparqlCondition = "?document <http://www.ftn.uns.ac.rs/rdf/zahtev_za_sertifikat/predicate/PodneoJmbg> \"" + jmbg + "\" ;";

        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }

        }
        return nodes;
    }

    @Override
    public ArrayList<ZahtevZaIzdavanjeSertifikata> searchByJMBG(SearchDTO searchDTO) throws Exception {
        ArrayList<RDFNode> nodes = searchRDF(searchDTO);
        ArrayList<ZahtevZaIzdavanjeSertifikata> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = findById(parts[parts.length - 1]);
            list.add(zahtevZaIzdavanjeSertifikata);
        }
        return list;
    }

    @Override
    public ArrayList<ZahtevZaIzdavanjeSertifikata> searchMetadata(MetadataSearchDTO metadataSearchDTO) throws Exception {
        String value = metadataSearchDTO.getSearch();
        String sparqlCondition = "?document ?d \"" + value + "\" .";
        ArrayList<RDFNode> nodes = new ArrayList<>();
        try (RDFReadResult result = FusekiReader.readRDFWithSparqlQuery("/zahtev_za_sertifikat", sparqlCondition)) {
            List<String> columnNames = result.getResult().getResultVars();
            while (result.getResult().hasNext()) {
                QuerySolution row = result.getResult().nextSolution();
                String columnName = columnNames.get(0);
                nodes.add(row.get(columnName));
            }
        }
        ArrayList<ZahtevZaIzdavanjeSertifikata> list = new ArrayList<>();
        for (RDFNode node : nodes) {
            String[] parts = node.toString().split("/");
            ZahtevZaIzdavanjeSertifikata zahtevZaIzdavanjeSertifikata = findById(parts[parts.length - 1]);
            list.add(zahtevZaIzdavanjeSertifikata);
        }
        return list;
    }

}
