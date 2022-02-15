package main.java.com.xml.officialbackend.service.contract;

import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface IService<T> {
    List<T> findAll() throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SAXException;
    T findById(String id) throws Exception;
    T create(T entity) throws Exception;
    T update(T entity, String id) throws Exception;
    void delete(String id) throws Exception;
}
