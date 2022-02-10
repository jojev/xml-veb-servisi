package main.java.com.xml.userbackend.service.implementation;

import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import main.java.com.xml.userbackend.repository.BaseRepository;
import main.java.com.xml.userbackend.service.contract.IInteresovanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InteresovanjeService implements IInteresovanjeService {

    private BaseRepository repository;

    @Autowired
    public InteresovanjeService(BaseRepository baseRepository) {
        this.repository = baseRepository;
    }

    @Override
    public String create(String interesovanjeZaVakcinisanje) throws Exception {
        String documentId = UUID.randomUUID().toString();
        this.repository.save("db/interesovanje", documentId ,interesovanjeZaVakcinisanje);
        return documentId;
    }
}
