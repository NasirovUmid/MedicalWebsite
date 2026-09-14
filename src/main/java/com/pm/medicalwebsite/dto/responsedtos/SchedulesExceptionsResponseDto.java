package com.pm.medicalwebsite.dto.responsedtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record SchedulesExceptionsResponseDto(
        UUID id,

        UUID doctorId,

        LocalDate exceptionDate,

        LocalTime startTime,

        LocalTime endTime,

        boolean isDayOff
) {
}
