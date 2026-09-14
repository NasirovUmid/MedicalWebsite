package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.SchedulesEntity;

import java.util.List;
import java.util.UUID;

public interface SchedulesDao {

    SchedulesEntity save(SchedulesEntity schedulesEntity);

    List<SchedulesEntity> findAll();

    SchedulesEntity getSchedulesEntityById(UUID id);

    void deactivateSchedule(UUID scheduleId);

}
