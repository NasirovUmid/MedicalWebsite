package com.pm.medicalwebsite.dto.responsedtos;

import com.pm.medicalwebsite.enums.DayOfWeek;

import java.time.LocalTime;
import java.util.UUID;

public record SchedulesResponseDto(
        UUID id,

        UUID doctorId,

        DayOfWeek dayOfWeek,

        LocalTime startTime,

        LocalTime endTime,

        boolean active
) {
}
