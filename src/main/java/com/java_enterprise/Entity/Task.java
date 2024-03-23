package com.java_enterprise.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    private Long id;

    private String name;

    private String description = null;

    private Status status = Status.CREATED;

    private Priority priority = Priority.NORMAL;

    private Date dateDeadlineAt = new Date();

    @JsonBackReference
    private Member member = null;
}