package com.java_enterprise.Exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public abstract HttpStatus getCode();
}