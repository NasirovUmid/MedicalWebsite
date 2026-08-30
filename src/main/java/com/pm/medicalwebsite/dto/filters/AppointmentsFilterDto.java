package com.pm.medicalwebsite.dto.filters;

import com.pm.medicalwebsite.enums.statuses.AppointmentStatus;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.UUID;

public record AppointmentsFilterDto(

        UUID doctorId,

        UUID patientId,

        UUID serviceId,
        @PastOrPresent
        Instant appointmentDate,

        AppointmentStatus status,
        @PastOrPresent
        Instant createdAt
) {
}
