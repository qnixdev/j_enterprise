package com.java_enterprise.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Member {
    private Long id;

    private String name;

    @JsonManagedReference
    List<Task> tasks = new ArrayList<>();
}