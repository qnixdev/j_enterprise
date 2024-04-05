package com.task_management_system.Enum;

import lombok.*;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Status {
    CREATED("created"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    private final String name;

    public static Status fromString(String value) {
        if (null == value) {
            return null;
        }

        return Stream.of(Status.values())
            .filter(s -> s.getName().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
        ;
    }
}