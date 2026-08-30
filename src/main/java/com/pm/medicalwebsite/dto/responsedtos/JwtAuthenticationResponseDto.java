package com.pm.medicalwebsite.dto.responsedtos;

import java.util.UUID;

public record JwtAuthenticationResponseDto(
        UUID userId,
        String accessToken,
        String refreshToken
) {
}
