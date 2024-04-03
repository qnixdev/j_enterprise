package com.task_management_system.Controller;

import com.task_management_system.Entity.Task;
import com.task_management_system.Request.Task.TaskCreateRequest;
import com.task_management_system.Request.Task.TaskDelegateRequest;
import com.task_management_system.Request.Task.TaskStatusRequest;
import com.task_management_system.Request.Task.TaskUpdateRequest;
import com.task_management_system.Service.Task.TaskCrudService;
import com.task_management_system.Service.Task.TaskRelationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskCrudService taskCrudService;
    private final TaskRelationService taskRelationService;

    public TaskController(
        TaskCrudService taskCrudService,
        TaskRelationService taskRelationService
    ) {
        this.taskCrudService = taskCrudService;
        this.taskRelationService = taskRelationService;
    }

    @GetMapping("/list")
    public Iterable<Task> listAll() {
        return this.taskCrudService.list();
    }

    @PatchMapping("/delegate")
    public ResponseEntity<Task> delegate(@Valid @RequestBody TaskDelegateRequest request) throws Exception {
        Task task = this.taskRelationService.delegate(request);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/change-status")
    public ResponseEntity<Task> status(@Valid @RequestBody TaskStatusRequest request) throws Exception {
        Task task = this.taskRelationService.changeStatus(request);

        return ResponseEntity.ok(task);
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