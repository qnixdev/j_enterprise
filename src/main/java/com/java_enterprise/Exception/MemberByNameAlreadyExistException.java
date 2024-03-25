package com.java_enterprise.Exception;

import org.springframework.http.HttpStatus;

public class MemberByNameAlreadyExistException extends AppException {
    private static final String FORMAT = "Member with name '%s' already exist.";

    public MemberByNameAlreadyExistException(String name) {
        super(String.format(FORMAT, name));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}