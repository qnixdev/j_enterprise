package com.java_enterprise.Request.Task;

import com.java_enterprise.Enum.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TaskStatusRequest {
    @NotNull
    private Long taskId;

    @NotNull
    private Status status;
}