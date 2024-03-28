package com.task_management_system.Controller.Task;

import com.task_management_system.Entity.Task;
import com.task_management_system.Request.Task.TaskDelegateRequest;
import com.task_management_system.Request.Task.TaskStatusRequest;
import com.task_management_system.Service.Task.TaskRelationService;
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