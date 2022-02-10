package main.java.com.xml.userbackend.rdf;

import main.java.com.xml.userbackend.util.FusekiAuthenticationUtilities;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;

import java.io.IOException;

public class FusekiReader {

    public static RDFReadResult readRDF(String graphUri) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        System.out.println("[INFO] Selecting the triples from the named graph \"" + graphUri + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + graphUri, "?s ?p ?o");

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        return new RDFReadResult(results, query);
    }
}
