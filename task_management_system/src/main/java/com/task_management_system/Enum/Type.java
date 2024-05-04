package com.task_management_system.Enum;

import lombok.*;

@AllArgsConstructor
@Getter
public enum Type {
    TASK_CREATED("task_created"),
    TASK_DELEGATE("task_delegate");

    private final String name;
}