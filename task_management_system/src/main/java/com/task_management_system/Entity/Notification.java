package com.task_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task_management_system.Enum.Type;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonBackReference
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private Member member = null;

    @Column(name = "message", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 63, nullable = false)
    private Type type;

    @Column(name = "date_created_at", nullable = false)
    private Date dateCreatedAt = new Date();

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;
}