package com.pm.medicalwebsite.dto.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequestDto(

        @NotBlank @Size(min = 8, max = 30)
        String oldPassword,
        @NotBlank @Size(min = 8, max = 30)
        String newPassword
) {
}
