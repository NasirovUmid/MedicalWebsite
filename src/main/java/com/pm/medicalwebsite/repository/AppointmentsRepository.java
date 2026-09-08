package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.AppointmentsEntity;
import com.pm.medicalwebsite.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentsRepository extends JpaRepository<AppointmentsEntity, UUID>, JpaSpecificationExecutor<AppointmentsEntity> {

    AppointmentsEntity save(AppointmentsEntity appointmentsEntity);

    Page<AppointmentsEntity> findAll(Specification<AppointmentsEntity> specification, Pageable pageable);

    @Query(value = "SELECT * FROM appointments WHERE patient_id = :userId AND appointment_date > now() AND status = 'SCHEDULED'", nativeQuery = true)
    Optional<AppointmentsEntity> getUpcomingAppointment(@Param("userId") UUID userId);

    Page<AppointmentsEntity> getAppointmentsEntitiesByPatientId(UUID patientId, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE appointments SET status = 'CANCELLED' WHERE id = :id", nativeQuery = true)
    void cancelAppointment(@Param("id") UUID id);
}
