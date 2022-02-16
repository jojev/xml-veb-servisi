package main.java.com.xml.userbackend.service.contract;


import main.java.com.xml.userbackend.model.interesovanje.InteresovanjeZaVakcinisanje;
import org.apache.jena.rdf.model.RDFNode;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public interface IInteresovanjeService extends IService<InteresovanjeZaVakcinisanje> {

    RDFNode getInteresovanje(String jmbg) throws IOException;

    InteresovanjeZaVakcinisanje create(InteresovanjeZaVakcinisanje interesovanjeZaVakcinisanje) throws Exception;

    ArrayList<RDFNode> searchRDF(String search) throws IOException;

    ArrayList<InteresovanjeZaVakcinisanje> searchByJMBG(String search) throws Exception;

    ArrayList<InteresovanjeZaVakcinisanje> searchByText(String search) throws IOException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException, SAXException;
}
