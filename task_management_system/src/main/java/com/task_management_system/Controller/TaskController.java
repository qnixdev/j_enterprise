package com.task_management_system.Controller;

import com.task_management_system.Entity.Task;
import com.task_management_system.Request.Task.TaskCreateRequest;
import com.task_management_system.Request.Task.TaskDelegateRequest;
import com.task_management_system.Request.Task.TaskStatusRequest;
import com.task_management_system.Request.Task.TaskUpdateRequest;
import com.task_management_system.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public Iterable<Task> listAll() {
        return this.taskService.list();
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskCreateRequest request) throws Exception {
        var task = this.taskService.create(request);

        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> read(@PathVariable UUID id) throws Exception {
        var task = this.taskService.read(id);

        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> update(@Valid @RequestBody TaskUpdateRequest request) throws Exception {
        var task = this.taskService.read(request.getId());

        return ResponseEntity.ok(this.taskService.update(task, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) throws Exception {
        var task = this.taskService.read(id);
        this.taskService.delete(task);

        return ResponseEntity.ok(true);
    }

    @PatchMapping("/delegate")
    public ResponseEntity<Task> delegate(@Valid @RequestBody TaskDelegateRequest request) throws Exception {
        Task task = this.taskService.delegate(request);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/change-status")
    public ResponseEntity<Task> status(@Valid @RequestBody TaskStatusRequest request) throws Exception {
        Task task = this.taskService.changeStatus(request);

        return ResponseEntity.ok(task);
    }
}