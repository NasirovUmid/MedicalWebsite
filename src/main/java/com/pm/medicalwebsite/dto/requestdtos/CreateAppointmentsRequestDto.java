package com.pm.medicalwebsite.dto.requestdtos;

import com.pm.medicalwebsite.enums.statuses.AppointmentStatus;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.Instant;
import java.util.UUID;

public record CreateAppointmentsRequestDto(

        UUID doctorId,
        UUID patientId,
        UUID serviceId,
        @FutureOrPresent
        Instant appointmentDate,
        AppointmentStatus status
) {
}
