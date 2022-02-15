package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.model.potvrda_o_vakcinaciji.PotvrdaOVakcinaciji;
import main.java.com.xml.officialbackend.rdf.FusekiWriter;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IListaCekanjaService;
import main.java.com.xml.officialbackend.service.contract.IPotvrdaOVakcinacijiService;
import main.java.com.xml.officialbackend.service.contract.ITerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
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
        return null;
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
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
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
}
