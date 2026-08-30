package com.pm.medicalwebsite.dto.requestdtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateMedicalRecordsDto(
        UUID appointmentId,
        @NotBlank
        String diagnosis,
        @NotBlank
        String treatment,
        @NotBlank
        String comment
) {
}
