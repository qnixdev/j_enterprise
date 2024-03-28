package com.task_management_system.Request.Member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {
    @NotNull
    private Long id;

    @Size(min = 2, max = 255)
    private String name;
}