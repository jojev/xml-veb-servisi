package main.java.com.xml.officialbackend.rdf;

import main.java.com.xml.officialbackend.util.FusekiAuthenticationUtilities;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

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

    public static RDFReadResult readRDFWithSparqlQuery(String graphUri, String sparqlCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        System.out.println("[INFO] Selecting the triples from the named graph \"" + graphUri + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + graphUri, sparqlCondition);
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        return new RDFReadResult(results, query);
    }
}
