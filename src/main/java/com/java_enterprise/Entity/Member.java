package com.java_enterprise.Entity;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Member {
    private Long id;

    private String name;

    List<Task> tasks = new ArrayList<>();
}