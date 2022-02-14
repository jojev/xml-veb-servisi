package main.java.com.xml.userbackend.service.contract;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(String id) throws Exception;
    T create(T entity) throws Exception;
    T update(T entity, String id) throws Exception;
    void delete(String id) throws Exception;
}