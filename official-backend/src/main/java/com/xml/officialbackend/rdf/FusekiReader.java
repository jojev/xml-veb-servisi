package main.java.com.xml.officialbackend.rdf;

import main.java.com.xml.officialbackend.util.FusekiAuthenticationUtilities;
import main.java.com.xml.officialbackend.rdf.RDFReadResult;
import main.java.com.xml.officialbackend.rdf.SparqlUtil;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;

import java.io.ByteArrayOutputStream;
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

    public static String readMetadata(String graphUri, String sparqlCondition, String format) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        String sparqlQuery = SparqlUtil.constructData(conn.dataEndpoint + graphUri, sparqlCondition);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        Model model = query.execConstruct();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, format);

        return new String(out.toByteArray());
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
    
    public static RDFReadResult readRDFWithSparqlCountQuery(String graphUri, String sparqlCondition) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        System.out.println("[INFO] Selecting the triples from the named graph \"" + graphUri + "\".");
        String sparqlQuery = SparqlUtil.selectCount(conn.dataEndpoint + graphUri, sparqlCondition);
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        return new RDFReadResult(results, query);
    }
}
