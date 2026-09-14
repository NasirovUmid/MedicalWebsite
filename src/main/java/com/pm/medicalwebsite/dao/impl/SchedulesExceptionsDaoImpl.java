package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.SchedulesExceptionsDao;
import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.SchedulesExceptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SchedulesExceptionsDaoImpl implements SchedulesExceptionsDao {

    private final SchedulesExceptionsRepository schedulesExceptionsRepository;

    @Override
    public SchedulesExceptionsEntity save(SchedulesExceptionsEntity schedulesExceptionsEntity) {
        return schedulesExceptionsRepository.save(schedulesExceptionsEntity);
    }

    @Override
    public List<SchedulesExceptionsEntity> getUpcomingExceptions(UUID doctorId) {
        return schedulesExceptionsRepository.getUpcomingExceptions(doctorId);
    }

    @Override
    public SchedulesExceptionsEntity getSchedulesExceptionsEntityById(UUID id) {
        return schedulesExceptionsRepository.getSchedulesExceptionsEntityById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.DAY_OF_WEEK_NOT_FOUND.name()));
    }

    @Override
    public void deactivateScheduleException(UUID exceptionId) {
        schedulesExceptionsRepository.deactivateScheduleException(exceptionId);
    }
}
