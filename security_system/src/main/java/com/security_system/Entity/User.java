package com.security_system.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonManagedReference
    @ManyToMany(targetEntity = SecurityGroup.class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_security_groups",
        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_security_group", referencedColumnName = "id")
    )
    private Set<SecurityGroup> securityGroups = new HashSet<>();
}