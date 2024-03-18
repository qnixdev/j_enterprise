package com.java_enterprise.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Random;

@AllArgsConstructor
@Getter
public enum Priority {
    HIGH("high"),
    NORMAL("normal"),
    LOW("low");

    private final String name;

    public static Priority getRandomPriority() {
        return values()[new Random().nextInt(values().length)];
    }
}