package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.MedicalRecordsEntity;

import java.util.UUID;

public interface MedicalRecordsDao {

    MedicalRecordsEntity save(MedicalRecordsEntity medicalRecordsEntity);

    MedicalRecordsEntity getMedicalRecordsEntitiesByAppointmentId(UUID appointmentId);

    MedicalRecordsEntity getMedicalRecordsEntitiesById(UUID id);

}
