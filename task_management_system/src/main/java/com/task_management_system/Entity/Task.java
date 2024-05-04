package com.task_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task_management_system.Enum.Priority;
import com.task_management_system.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 63)
    private Status status = Status.CREATED;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 63)
    private Priority priority = Priority.NORMAL;

    @Column(name = "date_deadline_at")
    private Date dateDeadlineAt = new Date();

    @JsonBackReference
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private Member member = null;
}