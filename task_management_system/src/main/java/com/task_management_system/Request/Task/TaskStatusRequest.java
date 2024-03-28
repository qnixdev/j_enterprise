package com.task_management_system.Request.Task;

import com.task_management_system.Enum.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TaskStatusRequest {
    @NotNull
    private Long taskId;

    @NotNull
    private Status status;
}