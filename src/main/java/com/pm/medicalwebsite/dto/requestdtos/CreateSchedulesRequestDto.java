package com.pm.medicalwebsite.dto.requestdtos;

import com.pm.medicalwebsite.enums.DayOfWeek;

import java.time.LocalTime;
import java.util.UUID;

public record CreateSchedulesRequestDto(

        UUID doctorId,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        boolean active
) {
}
