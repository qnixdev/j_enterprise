package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;

public class MemberByIdNotFoundException extends AppException {
    private static final String FORMAT = "Member with id '%d' not found.";

    public MemberByIdNotFoundException(Long id) {
        super(String.format(FORMAT, id));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }
}