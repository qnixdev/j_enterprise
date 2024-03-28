package com.task_management_system.Controller.Task;

import com.task_management_system.Entity.Task;
import com.task_management_system.Service.Task.TaskCrudService;
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