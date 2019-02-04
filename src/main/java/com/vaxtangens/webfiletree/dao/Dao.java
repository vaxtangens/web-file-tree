package com.vaxtangens.webfiletree.dao;

import java.util.List;

public interface Dao<T, PK> {
    T insert(T entity);

    T find(PK id);

    List<T> findAll();

    void update(T entity);

    void delete(PK id);
}
