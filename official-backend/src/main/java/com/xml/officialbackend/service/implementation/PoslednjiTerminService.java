package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.exception.MissingEntityException;
import main.java.com.xml.officialbackend.model.poslednji_termin.PoslednjiTermin;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IPoslednjiTermin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoslednjiTerminService implements IPoslednjiTermin {
    private BaseRepository baseRepository;

    @Autowired
    public PoslednjiTerminService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<PoslednjiTermin> findAll() {
        return null;
    }

    @Override
    public PoslednjiTermin findById(String id) throws Exception {
    	try {
    		return baseRepository.findById("/db/poslednji_termin", id, PoslednjiTermin.class);
    	}
    	catch(MissingEntityException|NullPointerException e) {
    		return null;
    	}
        
    }

    @Override
    public PoslednjiTermin create(PoslednjiTermin entity) throws Exception {
        String terminId = entity.getDatum().getYear() + "-" + entity.getDatum().getMonth() + "-" + entity.getDatum().getDay();

        baseRepository.save("/db/poslednji_termin", terminId, entity, PoslednjiTermin.class);
        return findById(terminId);
    }

    @Override
    public PoslednjiTermin update(PoslednjiTermin entity, String id) throws Exception {
        baseRepository.findById("/db/poslednji_termin", id, PoslednjiTermin.class);

        baseRepository.update("/db/poslednji_termin", id, "/poslednji_termin/broj_termina", String.valueOf(entity.getBrojTermina()),
                "http://www.ftn.uns.ac.rs/poslednji_termin");

        return this.findById(id);
    }

    @Override
    public void delete(String id) throws Exception {

    }
}
