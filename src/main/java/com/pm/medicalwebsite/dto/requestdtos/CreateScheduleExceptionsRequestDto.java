package com.pm.medicalwebsite.dto.requestdtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateScheduleExceptionsRequestDto(

        UUID doctorId,

        LocalDate exceptionDate,

        LocalTime startTime,

        LocalTime endTime,

        boolean isDayOff

) {
}
