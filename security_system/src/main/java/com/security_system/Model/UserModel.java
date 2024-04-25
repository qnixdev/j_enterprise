package com.security_system.Model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserModel {
    @NotNull
    @Size(min = 5, max = 255)
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    private String password;

    @NotNull
    @Size(min = 1, max = 255)
    private String confirmPassword;
}