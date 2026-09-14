package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.SchedulesDao;
import com.pm.medicalwebsite.datasource.SchedulesDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateSchedulesRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesResponseDto;
import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.mapper.SchedulesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SchedulesDatasourceImpl implements SchedulesDatasource {

    private final SchedulesDao schedulesDao;
    private final SchedulesMapper schedulesMapper;

    @Override
    public SchedulesResponseDto save(CreateSchedulesRequestDto createSchedulesRequestDto) {
        return schedulesMapper.toDto(schedulesDao.save(new SchedulesEntity(
                null, createSchedulesRequestDto.doctorId(), createSchedulesRequestDto.dayOfWeek(), createSchedulesRequestDto.startTime(),
                createSchedulesRequestDto.endTime(), createSchedulesRequestDto.active())));
    }

    @Override
    public List<SchedulesResponseDto> findAll() {
        return schedulesDao.findAll().stream().map(schedulesMapper::toDto).toList();
    }

    @Override
    public SchedulesResponseDto getSchedulesEntityById(UUID id) {
        return schedulesMapper.toDto(schedulesDao.getSchedulesEntityById(id));
    }

    @Override
    public void deactivateSchedule(UUID scheduleId) {
        schedulesDao.deactivateSchedule(scheduleId);
    }
}
