package com.java_enterprise.Request.Task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TaskDelegateRequest {
    @NotNull
    private Long taskId;

    @NotNull
    private Long memberId;
}