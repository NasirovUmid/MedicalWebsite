package com.pm.medicalwebsite.dto.requestdtos;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(
        @NotBlank
        String refreshToken
) {
}
