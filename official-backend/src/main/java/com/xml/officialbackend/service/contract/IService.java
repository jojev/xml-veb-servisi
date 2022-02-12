package main.java.com.xml.officialbackend.service.contract;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    T findById(Integer id) throws Exception;
    T create(T entity) throws Exception;
    T update(T entity, String id) throws Exception;
    void delete(Integer id) throws Exception;
}
