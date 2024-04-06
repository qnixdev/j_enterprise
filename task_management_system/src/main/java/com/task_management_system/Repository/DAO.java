package com.task_management_system.Repository;

import java.util.Optional;

public interface DAO<T> {
    Long nextId();

    Optional<T> find(Long id);

    Iterable<T> findAll();
}