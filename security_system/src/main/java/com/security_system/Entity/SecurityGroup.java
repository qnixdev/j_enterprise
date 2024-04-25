package com.security_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "security_group")
public class SecurityGroup {
    public enum Type { ADMIN, USER }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "type", length = 63, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonBackReference
    @ManyToMany(targetEntity = User.class, mappedBy = "securityGroups")
    private Set<User> users = new HashSet<>();
}