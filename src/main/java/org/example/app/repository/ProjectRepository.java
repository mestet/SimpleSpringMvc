package org.example.app.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository<T> {
    void store(T obj);

    List<T> retrieveAll();

    boolean removeById(Integer id);
}
