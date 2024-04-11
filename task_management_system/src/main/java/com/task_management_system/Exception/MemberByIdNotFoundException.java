package com.task_management_system.Exception;

import org.springframework.http.HttpStatus;
import java.util.UUID;

public class MemberByIdNotFoundException extends AppException {
    private static final String FORMAT = "Member with id: '%s' not found.";

    public MemberByIdNotFoundException(UUID id) {
        super(String.format(FORMAT, id.toString()));
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.NOT_FOUND;
    }
}