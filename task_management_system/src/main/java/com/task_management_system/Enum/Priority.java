package com.task_management_system.Enum;

import lombok.*;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Priority {
    HIGH("high"),
    NORMAL("normal"),
    LOW("low");

    private final String name;

    public static Priority fromString(String value) {
        if (null == value) {
            return null;
        }

        return Stream.of(Priority.values())
            .filter(p -> p.getName().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
        ;
    }
}