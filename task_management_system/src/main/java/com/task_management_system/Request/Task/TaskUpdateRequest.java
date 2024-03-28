package com.task_management_system.Request.Task;

import com.task_management_system.Enum.Priority;
import com.task_management_system.Enum.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.Date;

@Getter
public class TaskUpdateRequest {
    @NotNull
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(min = 2, max = 255)
    private String description;

    private Status status;

    private Priority priority;

    private Date deadline;

    private Long memberId;
}