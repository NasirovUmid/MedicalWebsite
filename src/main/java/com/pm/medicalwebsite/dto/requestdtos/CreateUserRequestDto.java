package com.pm.medicalwebsite.dto.requestdtos;

import com.pm.medicalwebsite.enums.statuses.UserStatus;
import com.pm.medicalwebsite.enums.UsersRoleTypes;
import jakarta.validation.constraints.*;

import java.time.Instant;

public record CreateUserRequestDto(

        @NotBlank
        String fullName,

        @Email
        String email,

        @NotBlank @Size(min = 8, max = 30)
        String password,

        @NotBlank
        @Pattern(regexp = "^(\\+)?998\\d{9}$")
        String phoneNumber,

        @Past
        Instant birthDate,

        @NotNull
        UsersRoleTypes role,

        @NotNull
        UserStatus status
) {
}
