package com.java_enterprise.Repository;

import com.java_enterprise.Entity.Task;
import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
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

    public List<Task> findBy(Status status) {
        return this.tasks.values()
            .stream()
            .filter(t -> t.getStatus() == status)
            .toList()
        ;
    }

    public List<Task> findBy(Priority priority) {
        return this.tasks.values()
            .stream()
            .filter(t -> t.getPriority() == priority)
            .toList()
        ;
    }

    public List<Task> findBy(Date dateDeadlineAt) {
        return this.tasks.values()
            .stream()
            .filter(t -> t.getDateDeadlineAt().before(dateDeadlineAt))
            .toList()
        ;
    }
}