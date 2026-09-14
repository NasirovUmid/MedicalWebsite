package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateScheduleExceptionsRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;

import java.util.List;
import java.util.UUID;

public interface SchedulesExceptionsDatasource {

    SchedulesExceptionsResponseDto save(CreateScheduleExceptionsRequestDto createScheduleExceptionsRequestDto);

    List<SchedulesExceptionsResponseDto> getUpcomingExceptions(UUID doctorId);

    SchedulesExceptionsResponseDto getSchedulesExceptionsEntityById(UUID id);

    void deactivateScheduleException(UUID exceptionId);

}
