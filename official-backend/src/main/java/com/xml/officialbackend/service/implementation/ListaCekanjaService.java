package main.java.com.xml.officialbackend.service.implementation;

import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.model.lista_cekanja.ListaCekanja;
import main.java.com.xml.officialbackend.rdf.MetadataExtractor;
import main.java.com.xml.officialbackend.repository.BaseRepository;
import main.java.com.xml.officialbackend.service.contract.IListaCekanjaService;
import org.exist.xquery.functions.util.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import java.util.List;

@Service
public class ListaCekanjaService implements IListaCekanjaService {
    private BaseRepository baseRepository;

    private ExistDbManager existDbManager;

    private MetadataExtractor metadataExtractor;

    @Autowired
    public ListaCekanjaService(BaseRepository baseRepository, ExistDbManager existDbManager, MetadataExtractor metadataExtractor) {
        this.baseRepository = baseRepository;
        this.existDbManager = existDbManager;
        this.metadataExtractor = metadataExtractor;
    }

    @Override
    public List<ListaCekanja> findAll() {
        return null;
    }

    @Override
    public ListaCekanja findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public ListaCekanja create(ListaCekanja entity) throws Exception {
        String queueName = "ListaCekanja";
        
        baseRepository.save("/db/cekanje", queueName, entity, ListaCekanja.class);
        XMLResource resource = existDbManager.load("/db/cekanje", queueName);
        System.out.println(resource.getContent().toString());
        return entity;
    }

    @Override
    public ListaCekanja update(ListaCekanja entity, Integer id) throws Exception {
       
    	return entity;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public ListaCekanja addPatientToQueue(ListaCekanja.Stavka stavka) {
        return null;
    }
}
