package com.java_enterprise.Entity;

import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
import lombok.*;
import java.text.SimpleDateFormat;
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

    private Member member = null;

    public String toString() {
        return "Task(id=" + this.getId()
            + ", name=" + this.getName()
            + ", description=" + this.getDescription()
            + ", status=" + this.getStatus()
            + ", priority=" + this.getPriority()
            + ", deadline=" + new SimpleDateFormat("yyyy-MM-dd").format(this.getDateDeadlineAt())
            + ")"
        ;
    }
}