package com.pm.medicalwebsite.dto.requestdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @Email
        String email,
        @Size(min = 8, max = 30) @NotBlank
        String password
) {
}
