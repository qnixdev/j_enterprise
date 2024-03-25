package com.java_enterprise.Request.Task;

import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
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