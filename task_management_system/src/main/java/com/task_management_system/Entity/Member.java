package com.task_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Member {
    private UUID id;

    private String name;

    @JsonManagedReference
    List<Task> tasks = new ArrayList<>();
}