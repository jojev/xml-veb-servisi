package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.model.stanjevakcine.StanjeVakcine;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IVaccineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class VaccineStatusService implements IVaccineStatusService {
    private BaseRepository baseRepository;

    @Autowired
    public VaccineStatusService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<StanjeVakcine> findAll() {
        return null;
    }

    @Override
    public StanjeVakcine findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public StanjeVakcine create(StanjeVakcine entity) throws Exception {
        String vaccineStatusId = UUID.randomUUID().toString();
        entity.setAbout("http://www.ftn.uns.ac.rs/rdf/korisnici/" + vaccineStatusId);

        baseRepository.save("/db/stanjeVakcine", vaccineStatusId, entity, StanjeVakcine.class);

        return entity;
    }

    @Override
    public StanjeVakcine update(StanjeVakcine entity, String id) throws Exception {
        StanjeVakcine stanjeVakcine = baseRepository.findById("/db/stanjeVakcine", id, StanjeVakcine.class);

        baseRepository.update("/db/stanjeVakcine", id, "/stanjeVakcine/kolicina", String.valueOf(entity.getKolicina()),
                "http://www.ftn.uns.ac.rs/stanjeVakcine");

        return baseRepository.findById("/db/stanjeVakcine", id, StanjeVakcine.class);

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public List<StanjeVakcine> findByHospitalAndVaccine(String hospital, String vaccine) {
        return null;
    }
}
