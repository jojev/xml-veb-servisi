package main.java.com.xml.officialbackend.repository;


import main.java.com.xml.officialbackend.existdb.ExistDbManager;
import main.java.com.xml.officialbackend.jaxb.JaxBParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    public void append(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.appendContent(collectionUri, documentId, contextPath, newValue, targetNamespace);

    }

    public void insertAfter(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
             throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.insertAfter(collectionUri, documentId, contextPath, newValue, targetNamespace);

    }

    public void removeElement(String collectionUri, String documentId, String contextPath, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.remove(collectionUri, documentId, contextPath, targetNamespace);
    }

    public void insertAsLastNode(String collectionUri, String documentId, String contextPath, String node, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.insertAsLastNode(collectionUri, documentId, contextPath, node, targetNamespace);
    }

    public void removeNode(String collectionUri, String documentId, String contextPath, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existDbManager.removeNode(collectionUri, documentId, contextPath, targetNamespace);

    }

    public  <T>  List<T> loadAllDocumentsFromCollection(String collectionUri, String targetNamespace, String xqueryExpression, Class genericClass)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException {
        final List<T> allDocuments = new ArrayList<>();
        for (Resource resource: existDbManager.loadAllDocumentsInCollection(collectionUri, targetNamespace, xqueryExpression)) {
            allDocuments.add(jaxBParser.<T>unmarshall((XMLResource) resource, genericClass));
        }
        return allDocuments;

    }
}
