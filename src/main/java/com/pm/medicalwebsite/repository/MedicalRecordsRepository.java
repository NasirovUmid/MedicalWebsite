package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.MedicalRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecordsEntity, UUID> {

    MedicalRecordsEntity save(MedicalRecordsEntity medicalRecordsEntity);

    Optional<MedicalRecordsEntity> getMedicalRecordsEntitiesByAppointmentId(UUID appointmentId);

    Optional<MedicalRecordsEntity> getMedicalRecordsEntitiesById(UUID id);

}
