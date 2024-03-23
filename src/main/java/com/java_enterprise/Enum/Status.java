package com.java_enterprise.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    CREATED("created"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    private final String name;
}