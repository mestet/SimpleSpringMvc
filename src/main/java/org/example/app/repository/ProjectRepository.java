package org.example.app.repository;

import java.util.List;

public interface ProjectRepository<T> {
    boolean store(T obj);

    List<T> retrieveAll();

    boolean remove(T obj);
}
