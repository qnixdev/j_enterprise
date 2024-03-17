package com.java_enterprise.Service;

import com.java_enterprise.Entity.Task;
import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
import com.java_enterprise.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(
        String name,
        String description,
        Status status,
        Priority priority,
        Date dateDeadlineAt
    ) {
        var task = Task.builder()
            .id(this.taskRepository.nextId())
            .name(name)
            .description(description)
            .status(status)
            .priority(priority)
            .dateDeadlineAt(dateDeadlineAt)
            .build()
        ;
        this.taskRepository.add(task);

        return task;
    }

    public void removeTask(Task task) {
        this.taskRepository.remove(task);
    }

    public void changeStatus(Task task, Status status) {
        task.setStatus(status);
    }

    public List<Task> getTasksByStatus(Status status) {
        return this.taskRepository.findBy(status);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return this.taskRepository.findBy(priority);
    }

    public List<Task> getTasksBeforeDateDeadline(Date dateDeadlineAt) {
        return this.taskRepository.findBy(dateDeadlineAt);
    }
}