package com.java_enterprise.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Random;

@AllArgsConstructor
@Getter
public enum Status {
    CREATED("created"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    private final String name;

    public static Status getRandomStatus() {
        return values()[new Random().nextInt(values().length)];
    }
}