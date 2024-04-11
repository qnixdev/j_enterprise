package com.task_management_system.Repository;

import com.task_management_system.Entity.Task;
import java.util.Optional;
import java.util.UUID;

public interface AsTaskRepository {
    Optional<Task> findById(UUID id);

    Iterable<Task> findAll();

    Task save(Task task);

    void delete(Task entity);

    boolean isExistByName(String name);

    boolean isExistByName(String name, UUID id);
}