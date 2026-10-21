package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.SchedulesEntity;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchedulesExceptionsRepository extends JpaRepository<SchedulesExceptionsEntity, UUID> {

    SchedulesExceptionsEntity save(SchedulesExceptionsEntity schedulesExceptionsEntity);

    @Query(value = "SELECT * FROM schedules_exceptions WHERE doctor_id = :doctorId AND exception_date >= CURRENT_DATE ORDER BY exception_date", nativeQuery = true)
    List<SchedulesExceptionsEntity> getUpcomingExceptions(UUID doctorId);

    Optional<SchedulesExceptionsEntity> getSchedulesExceptionsEntityById(UUID id);

    @Modifying
    @Query(value = "UPDATE schedules_exceptions SET is_day_off = true WHERE id = :exceptionId", nativeQuery = true)
    void deactivateScheduleException(@Param("exceptionId") UUID exceptionId);
}
