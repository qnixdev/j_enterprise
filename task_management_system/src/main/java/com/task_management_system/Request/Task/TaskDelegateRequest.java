package com.task_management_system.Request.Task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.UUID;

@Getter
public class TaskDelegateRequest {
    @NotNull
    private UUID taskId;

    @NotNull
    private UUID memberId;
}