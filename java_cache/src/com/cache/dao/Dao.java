package com.cache.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/*
 *
 * Interface for all DAO
 * Implements DAO Design Pattern to access information with CRUD operations
 * @author Ricardo Marcondes
 */
public interface Dao <T>{

    Optional<T> get(int id);

    Collection<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
