package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.SchedulesExceptionsDao;
import com.pm.medicalwebsite.datasource.SchedulesExceptionsDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateScheduleExceptionsRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateSchedulesRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;
import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;
import com.pm.medicalwebsite.mapper.SchedulesExceptionsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SchedulesExceptionDatasourceImpl implements SchedulesExceptionsDatasource {

    private final SchedulesExceptionsDao schedulesExceptionsDao;
    private final SchedulesExceptionsMapper schedulesExceptionsMapper;

    @Override
    public SchedulesExceptionsResponseDto save(CreateScheduleExceptionsRequestDto createScheduleExceptionsRequestDto) {
        return schedulesExceptionsMapper.toDto(schedulesExceptionsDao.save(new SchedulesExceptionsEntity(
                null, createScheduleExceptionsRequestDto.doctorId(), createScheduleExceptionsRequestDto.exceptionDate(),
                createScheduleExceptionsRequestDto.startTime(), createScheduleExceptionsRequestDto.endTime(), createScheduleExceptionsRequestDto.isDayOff()
        )));
    }

    @Override
    public List<SchedulesExceptionsResponseDto> getUpcomingExceptions(UUID doctorId) {
        return schedulesExceptionsDao.getUpcomingExceptions(doctorId).stream().map(schedulesExceptionsMapper::toDto).toList();
    }

    @Override
    public SchedulesExceptionsResponseDto getSchedulesExceptionsEntityById(UUID id) {
        return schedulesExceptionsMapper.toDto(schedulesExceptionsDao.getSchedulesExceptionsEntityById(id));
    }

    @Override
    public void deactivateScheduleException(UUID exceptionId) {
        schedulesExceptionsDao.deactivateScheduleException(exceptionId);
    }
}
