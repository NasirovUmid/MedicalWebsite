package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.SchedulesDatasource;
import com.pm.medicalwebsite.datasource.SchedulesExceptionsDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateScheduleExceptionsRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateSchedulesRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesListsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SchedulesUseCase {

    private final SchedulesDatasource schedulesDatasource;
    private final SchedulesExceptionsDatasource schedulesExceptionsDatasource;

    public SchedulesResponseDto createSchedules(CreateSchedulesRequestDto createSchedulesRequestDto) {

        return schedulesDatasource.save(createSchedulesRequestDto);

    }

    public SchedulesExceptionsResponseDto createSchedulesExceptions(CreateScheduleExceptionsRequestDto createScheduleExceptionsRequestDto) {

        return schedulesExceptionsDatasource.save(createScheduleExceptionsRequestDto);

    }

    public SchedulesListsResponseDto getActualSchedules(UUID doctorId) {

        return new SchedulesListsResponseDto(
                schedulesDatasource.findAll(),
                schedulesExceptionsDatasource.getUpcomingExceptions(doctorId)
        );
    }

    public void deactivateSchedules(UUID schedulesId) {
        schedulesDatasource.deactivateSchedule(schedulesId);
    }

    public void deactivateSchedulesExceptions(UUID schedulesExceptionId) {

        schedulesExceptionsDatasource.deactivateScheduleException(schedulesExceptionId);

    }

}
