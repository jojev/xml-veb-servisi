package main.java.com.xml.officialbackend.existdb;

import main.java.com.xml.officialbackend.existdb.template.XUpdateTemplate;
import main.java.com.xml.officialbackend.util.ExistAuthenticationUtilities;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Resource> executeXquery(String collectionUri, String targetNamespace, String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, IOException {
    	openConnection();
        Collection collection = null;
        List<Resource> resources = new ArrayList<Resource>();

        try {
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistAuthenticationUtilities.loadProperties().user,
                    ExistAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");
            
            // get an instance of xquery service
            XQueryService xqueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");
            
            // make the service aware of namespaces
            xqueryService.setNamespace("b", targetNamespace);
            
            CompiledExpression compiledXquery = xqueryService.compile(query);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while(i.hasMoreResources()) {
                
            	try {
            		res = i.nextResource();
                    resources.add(res);
                    System.out.println(res.getContent());
                    
                } finally {
                    
                	// don't forget to cleanup resources
                    try { 
                    	((EXistResource)res).freeResources(); 
                    } catch (XMLDBException xe) {
                    	xe.printStackTrace();
                    }
                }
            }            
        } catch (Exception e) {
            closeConnection(collection, null);
        }

        return resources;	
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

    public List<Resource> loadAllDocumentsInCollection(String collectionUri, String targetNamespace, String xqueryExpression) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        openConnection();
        Collection collection = null;
        List<Resource> resources = new ArrayList<Resource>();
        try {
            collection = DatabaseManager.getCollection(ExistAuthenticationUtilities.loadProperties().uri + collectionUri,
                    ExistAuthenticationUtilities.loadProperties().user,
                    ExistAuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");

            XQueryService xqueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            xqueryService.setNamespace("b", targetNamespace);

            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);
            ResourceIterator i = result.getIterator();
            Resource res = null;


            while(i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    resources.add(res);
                } finally {
                    try {
                        ((EXistResource)res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            closeConnection(collection, null);
        }
        closeConnection(collection, null);
        return resources;
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
        closeConnection(collection, resource);
    }

    public void insertAsLastNode(String collectionUri, String documentId, String contextPath, String node, String targetNamespace) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getAppendExpression(targetNamespace), contextPath, node));

        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }

    public void removeNode(String collectionUri, String documentId, String contextPath, String targetNamespace) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getRemoveExspression(targetNamespace), contextPath));

        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }
    public void appendContent(String collectionUri, String documentId, String contextPath, String newValue, String targetNamespace)
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

            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getAppendExpression(targetNamespace), contextPath, newValue));

        } catch (Exception e) {
            closeConnection(collection, resource);
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
            XUpdateQueryService xupdateService = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getInsertAfterExpresson(targetNamespace), contextPath, newValue));
        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }


    public void remove(String collectionUri, String documentId, String contextPath, String targetNamespace) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
            xupdateService.updateResource(documentId, String.format(XUpdateTemplate.getRemoveExspression(targetNamespace), contextPath));
        } catch (Exception e) {
            closeConnection(collection, resource);
        }
    }

}
