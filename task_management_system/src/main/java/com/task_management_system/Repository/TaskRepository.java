package com.task_management_system.Repository;

import com.task_management_system.Entity.Task;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();

    public Long nextId() {
        var nextId = this.tasks.keySet().size();

        return (long) ++nextId;
    }

    public void add(Task task) {
        if (!this.tasks.containsKey(task.getId())) {
            this.tasks.put(task.getId(), task);
        }
    }

    public void remove(Task task) {
        this.tasks.remove(task.getId());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(this.tasks.get(id));
    }

    public Iterable<Task> findAll() {
        return this.tasks.values();
    }

    public boolean isExistByName(String name) {
        return this.tasks.values()
            .stream()
            .anyMatch(m -> m.getName().equals(name))
        ;
    }

    public boolean isExistByName(String name, Long existTaskId) {
        return this.tasks.values()
            .stream()
            .anyMatch(m -> m.getName().equals(name) && !Objects.equals(m.getId(), existTaskId))
        ;
    }
}