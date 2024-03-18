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

    public void addTask(Task task) {
        if (!this.tasks.contains(task)) {
            this.tasks.add(task);
            task.setMember(this);
        }
    }

    public void removeTask(Task task) {
        if (this.tasks.remove(task)) {
            if (task.getMember() == this) {
                task.setMember(null);
            }
        }
    }
}