package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;

public class TaskByNameAlreadyExistException extends AppException {
    private static final String FORMAT = "Task with name '%s' already exist.";

    public TaskByNameAlreadyExistException(String name) {
        super(String.format(FORMAT, name));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}