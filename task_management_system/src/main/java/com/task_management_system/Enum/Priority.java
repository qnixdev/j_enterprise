package com.task_management_system.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    HIGH("high"),
    NORMAL("normal"),
    LOW("low");

    private final String name;
}