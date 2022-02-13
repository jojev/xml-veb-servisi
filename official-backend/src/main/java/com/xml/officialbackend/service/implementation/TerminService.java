package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.model.termin.Termin;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.ITerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TerminService implements ITerminService {
    private BaseRepository baseRepository;

    private MetadataExtractor metadataExtractor;

    @Autowired
    public TerminService(BaseRepository baseRepository, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.metadataExtractor = metadataExtractor;
    }

    @Override
    public List<Termin> findAll() {
        return null;
    }

    @Override
    public Termin findById(String id) throws Exception {
        return baseRepository.findById("/db/termini",id, Termin.class);
    }

    @Override
    public Termin create(Termin entity) throws Exception {
        String terminId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/temini/" + terminId);

        baseRepository.save("/db/termini", terminId, entity, Termin.class);
        return findById(terminId);
    }

    @Override
    public Termin update(Termin entity, String id) throws Exception {
        return null;
    }

    @Override
    public void delete(String id) throws Exception {

    }
}
