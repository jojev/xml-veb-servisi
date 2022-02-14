package main.java.com.xml.userbackend.service.contract;


import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import org.apache.jena.rdf.model.RDFNode;

import java.io.IOException;

public interface IInteresovanjeService extends IService<InteresovanjeZaVakcinisanje> {

    InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje) throws Exception;

    RDFNode searchRDF(String search) throws IOException;

    InteresovanjeZaVakcinisanje searchByJMBG(String search) throws Exception;
}
