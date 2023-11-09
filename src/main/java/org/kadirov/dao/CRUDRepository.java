package org.kadirov.dao;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, U> {
    List<T> findAll();
    Optional<T> findById(U id);
    T save(T entity);
    T update(T entity);
    void deleteById(U id);
}
