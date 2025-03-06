package ru.fmd.EvoTask4_13.repository;

import java.util.List;
import java.util.Optional;

public interface iRepository<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    void add(T t);
    int update(int id, T t);
    boolean delete(int id);
}
