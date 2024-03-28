package com.task_management_system.Api;

import com.task_management_system.Exception.AppException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException ex) {
        return ResponseEntity
            .status(ex.getCode())
            .body(new SimpleErrorBody(ex.getMessage()))
        ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(new MapErrorBody(this.getErrors(ex)))
        ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new SimpleErrorBody(ex.getMessage()))
        ;
    }

    private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (exist, next) -> exist))
        ;
    }

    @Getter
    @AllArgsConstructor
    private static class SimpleErrorBody {
        private final String message;
    }

    @Getter
    @AllArgsConstructor
    private static class MapErrorBody {
        private final Map<String, String> message;
    }
}