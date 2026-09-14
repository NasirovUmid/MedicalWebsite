package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;

import java.util.List;
import java.util.UUID;

public interface SchedulesExceptionsDao {

    SchedulesExceptionsEntity save(SchedulesExceptionsEntity schedulesExceptionsEntity);

    List<SchedulesExceptionsEntity> getUpcomingExceptions(UUID doctorId);

    SchedulesExceptionsEntity getSchedulesExceptionsEntityById(UUID id);

    void deactivateScheduleException(UUID exceptionId);
}
