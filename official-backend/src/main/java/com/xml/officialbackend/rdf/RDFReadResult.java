package main.java.com.xml.officialbackend.rdf;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;

import java.io.Closeable;

public class RDFReadResult implements Closeable {
    private ResultSet result;
    private QueryExecution query;

    public RDFReadResult() {

    }

    public RDFReadResult(ResultSet result, QueryExecution query) {
        this.result = result;
        this.query = query;
    }

    @Override
    public void close() {
        this.query.close();
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public void setQuery(QueryExecution query) {
        this.query = query;
    }
}
