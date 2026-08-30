package com.pm.medicalwebsite.dto.responsedtos;

import java.time.Instant;
import java.util.UUID;

public record MedicalRecordsResponseDto(
        UUID id,
        UUID appointmentId,
        String diagnosis,
        String treatment,
        String comment,
        Instant createdAt
) {
}
