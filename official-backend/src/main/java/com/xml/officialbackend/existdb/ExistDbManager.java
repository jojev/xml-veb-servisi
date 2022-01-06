package main.java.com.xml.officialbackend.existdb;

import main.java.com.xml.officialbackend.util.AuthenticationUtilities;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.exist.xmldb.EXistResource;

import javax.xml.transform.OutputKeys;
import java.io.IOException;

@Service
public class ExistDbManager {
    
    public void openConnection() throws XMLDBException, IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        Class<?> cl = Class.forName(AuthenticationUtilities.loadProperties().driver);

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
        XMLResource resource;
        try {
            collection = DatabaseManager.getCollection(AuthenticationUtilities.loadProperties().uri + collectionUri,
                    AuthenticationUtilities.loadProperties().user,
                    AuthenticationUtilities.loadProperties().password);
            collection.setProperty(OutputKeys.INDENT, "yes");
            resource = (XMLResource) collection.getResource(documentId);
            return resource;
        } finally {
            if (collection != null) {
                collection.close();
            }
        }
    }




}
