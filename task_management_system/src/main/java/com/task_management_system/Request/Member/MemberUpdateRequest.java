package com.task_management_system.Request.Member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.UUID;

@Getter
public class MemberUpdateRequest {
    @NotNull
    private UUID id;

    @Size(min = 2, max = 255)
    private String name;
}