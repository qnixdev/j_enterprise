package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;
import java.util.UUID;

public class TaskByIdNotFoundException extends AppException {
    private static final String FORMAT = "Task with id: '%s' not found.";

    public TaskByIdNotFoundException(UUID id) {
        super(String.format(FORMAT, id.toString()));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }
}