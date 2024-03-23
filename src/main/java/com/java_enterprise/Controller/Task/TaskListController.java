package com.java_enterprise.Controller.Task;

import com.java_enterprise.Entity.Task;
import com.java_enterprise.Service.Task.TaskCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskListController {
    private final TaskCrudService taskCrudService;

    @Autowired
    public TaskListController(TaskCrudService taskCrudService) {
        this.taskCrudService = taskCrudService;
    }

    @GetMapping
    public Iterable<Task> listAll() {
        return this.taskCrudService.list();
    }
}