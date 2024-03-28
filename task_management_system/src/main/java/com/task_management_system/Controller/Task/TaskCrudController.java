package com.task_management_system.Controller.Task;

import com.task_management_system.Entity.Task;
import com.task_management_system.Request.Task.TaskCreateRequest;
import com.task_management_system.Request.Task.TaskUpdateRequest;
import com.task_management_system.Service.Task.TaskCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskCrudController {
    private final TaskCrudService taskCrudService;

    @Autowired
    public TaskCrudController(TaskCrudService taskCrudService) {
        this.taskCrudService = taskCrudService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskCreateRequest request) throws Exception {
        var task = this.taskCrudService.create(request);

        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> read(@PathVariable Long id) throws Exception {
        var task = this.taskCrudService.read(id);

        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> update(@Valid @RequestBody TaskUpdateRequest request) throws Exception {
        var task = this.taskCrudService.read(request.getId());

        return ResponseEntity.ok(this.taskCrudService.update(task, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws Exception {
        var task = this.taskCrudService.read(id);
        this.taskCrudService.delete(task);

        return ResponseEntity.ok(true);
    }
}