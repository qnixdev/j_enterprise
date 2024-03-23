package com.java_enterprise.Controller.Task;

import com.java_enterprise.Entity.Task;
import com.java_enterprise.Request.Task.TaskDelegateRequest;
import com.java_enterprise.Request.Task.TaskStatusRequest;
import com.java_enterprise.Service.Task.TaskRelationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskRelationController {
    private final TaskRelationService taskRelationService;

    @Autowired
    public TaskRelationController(
        TaskRelationService taskRelationService
    ) {
        this.taskRelationService = taskRelationService;
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
}