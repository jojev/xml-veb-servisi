package main.java.com.xml.userbackend.repository;


import main.java.com.xml.userbackend.existdb.ExistDbManager;
import main.java.com.xml.userbackend.jaxb.JaxBParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;

import java.io.OutputStream;

@Repository
public class BaseRepository {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;

    public <T> void save(String collectionId, String documentId, T objectToSave) throws Exception {
        OutputStream os = jaxBParser.marshall(objectToSave);
        existDbManager.store(collectionId, documentId, os.toString());
    }

    public <T> T findById(String collectionId, String documentId, Class genericClass) throws Exception {
        XMLResource resource = existDbManager.load(collectionId, documentId);
        T loadedObject = jaxBParser.unmarshall(resource, genericClass);
        return loadedObject;
    }

    public <T> void save(String collectionId, String documentId, String stringToSave) throws Exception {
        existDbManager.store(collectionId, documentId, stringToSave);
    }

}