package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.SchedulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchedulesRepository extends JpaRepository<SchedulesEntity, UUID> {

    SchedulesEntity save(SchedulesEntity schedulesEntity);

    List<SchedulesEntity> findAll();

    Optional<SchedulesEntity> getSchedulesEntityById(UUID id);

    @Query(value = "UPDATE schedules SET active = false WHERE id = :scheduleId", nativeQuery = true)
    void deactivateSchedule(@Param("scheduleId") UUID scheduleId);
}
