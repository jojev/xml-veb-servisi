package main.java.com.xml.userbackend.existdb;

import main.java.com.xml.userbackend.existdb.template.XUpdateTemplate;
import main.java.com.xml.userbackend.util.ExistAuthenticationUtilities;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.transform.OutputKeys;
import java.io.IOException;

@Service
public class ExistDbManager {
    
    public void openConnection() throws XMLDBException, IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {

        Class<?> cl = Class.forName(ExistAuthenticationUtilities.loadProperties().driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    public void closeConnection(Collection collection, XMLResource resource) throws XMLDBException {
        if (collection != null) {
            collection.close();
        }
        if (resource != null) {
            ((EXistResource) resource).freeResources();
        }
    }

    public XMLResource load(String collectionUri, String documentId) throws Exception  {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;
        try {
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistAuthenticationUtilities.loadProperties().user,
                    ExistAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");
            resource = (XMLResource) collection.getResource(documentId);
        } catch (Exception e) {
            closeConnection(collection, resource);
        }
        return resource;
    }

    public void store(String collectionId, String documentId, String xmlString) throws Exception  {
        openConnection();
        Collection col = null;
        XMLResource res = null;

        try {
            col = getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(xmlString);
            col.storeResource(res);
        } finally {
            closeConnection(col, res);
        }
    }


    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException, IOException {

        Collection col = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                ExistAuthenticationUtilities.loadProperties().user,
                ExistAuthenticationUtilities.loadProperties().password);

        // create the collection if it does not exist
        if(col == null) {

            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + path,
                        ExistAuthenticationUtilities.loadProperties().user,
                        ExistAuthenticationUtilities.loadProperties().password);

                if (startCol == null) {
                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));

                    Collection parentCol = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + parentPath,
                            ExistAuthenticationUtilities.loadProperties().user,
                            ExistAuthenticationUtilities.loadProperties().password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }


    public void update(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;
        try {
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistAuthenticationUtilities.loadProperties().user,
                    ExistAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");

            XUpdateQueryService xupdateService = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getUpdateExpression(targetNamespace), contextPath, newValue));

        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }

    public void insertAfter(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
            throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        XMLResource resource =  null;
        try {
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistAuthenticationUtilities.loadProperties().user,
                    ExistAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");
            System.out.println("ANDRIJA");
            XUpdateQueryService xupdateService = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            System.out.println("VOJNOVIC");
            System.out.println(String.format(XUpdateTemplate.getInsertAfterExpresson(targetNamespace), contextPath, newValue));
            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getInsertAfterExpresson(targetNamespace), contextPath, newValue));
            System.out.println("KONJINA");
        } catch (Exception e) {
            System.out.println(e.getCause());
            closeConnection(collection, resource);
        }
    }



}
