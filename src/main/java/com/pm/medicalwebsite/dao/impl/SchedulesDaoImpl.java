package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.SchedulesDao;
import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.SchedulesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SchedulesDaoImpl implements SchedulesDao {

    private final SchedulesRepository schedulesRepository;

    @Override
    public SchedulesEntity save(SchedulesEntity schedulesEntity) {
        return schedulesRepository.save(schedulesEntity);
    }

    @Override
    public List<SchedulesEntity> findAll() {
        return schedulesRepository.findAll();
    }

    @Override
    public SchedulesEntity getSchedulesEntityById(UUID id) {
        return schedulesRepository.getSchedulesEntityById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.DAY_OF_WEEK_NOT_FOUND.name()));
    }

    @Override
    public void deactivateSchedule(UUID scheduleId) {
        schedulesRepository.deactivateSchedule(scheduleId);
    }
}
