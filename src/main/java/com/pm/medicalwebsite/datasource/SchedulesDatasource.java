package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateSchedulesRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesResponseDto;

import java.util.List;
import java.util.UUID;

public interface SchedulesDatasource {

    SchedulesResponseDto save(CreateSchedulesRequestDto createSchedulesRequestDto);

    List<SchedulesResponseDto> findAll();

    SchedulesResponseDto getSchedulesEntityById(UUID id);

    void deactivateSchedule(UUID scheduleId);


}
