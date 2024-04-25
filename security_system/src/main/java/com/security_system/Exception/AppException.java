package com.security_system.Exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends Exception {
    public AppException(String message) {
        super(message);
    }

    public abstract HttpStatus getCode();
}