package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;

public class TaskByIdNotFoundException extends AppException {
    private static final String FORMAT = "Task with id '%d' not found.";

    public TaskByIdNotFoundException(Long id) {
        super(String.format(FORMAT, id));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }
}