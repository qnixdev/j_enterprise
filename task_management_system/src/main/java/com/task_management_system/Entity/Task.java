package com.task_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task_management_system.Enum.Priority;
import com.task_management_system.Enum.Status;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    private UUID id;

    private String name;

    private String description = null;

    private Status status = Status.CREATED;

    private Priority priority = Priority.NORMAL;

    private Date dateDeadlineAt = new Date();

    @JsonBackReference
    private Member member = null;
}