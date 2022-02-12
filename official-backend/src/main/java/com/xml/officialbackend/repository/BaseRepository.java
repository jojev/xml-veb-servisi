package main.java.com.xml.officialbackend.repository;


import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class BaseRepository {
    @Autowired
    private ExistDbManager existDbManager;

    @Autowired
    private JaxBParser jaxBParser;

    public <T> void save(String collectionId, String documentId, T objectToSave, Class genericClass) throws Exception {
        OutputStream os = jaxBParser.marshall(objectToSave, genericClass);
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

    public void update(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.update(collectionUri, documentId, contextPath, newValue, targetNamespace);

    }
}
