package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.model.korisnik.Korisnik;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private BaseRepository baseRepository;

    @Autowired
    public UserService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
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
        String userId = UUID.randomUUID().toString();
        entity.setAbout(userId);
        baseRepository.save("/db/korisnik", userId, entity, Korisnik.class);
        return entity;
    }

    @Override
    public Korisnik update(Korisnik entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}
