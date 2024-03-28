package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public abstract HttpStatus getCode();
}