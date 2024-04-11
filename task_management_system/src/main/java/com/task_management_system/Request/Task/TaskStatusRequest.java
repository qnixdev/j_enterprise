package com.task_management_system.Request.Task;

import com.task_management_system.Enum.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.UUID;

@Getter
public class TaskStatusRequest {
    @NotNull
    private UUID taskId;

    @NotNull
    private Status status;
}