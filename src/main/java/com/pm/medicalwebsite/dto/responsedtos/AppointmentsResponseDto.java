package com.pm.medicalwebsite.dto.responsedtos;

import com.pm.medicalwebsite.enums.statuses.AppointmentStatus;

import java.time.Instant;
import java.util.UUID;

public record AppointmentsResponseDto(

        UUID id,
        UsersResponseDto doctor,
        UsersResponseDto patient,
        ServicesResponseDto service,
        Instant appointmentDate,
        AppointmentStatus status,
        Instant createdAt
        ) {
}
