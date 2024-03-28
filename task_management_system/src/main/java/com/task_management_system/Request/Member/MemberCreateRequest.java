package com.task_management_system.Request.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberCreateRequest {
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;
}