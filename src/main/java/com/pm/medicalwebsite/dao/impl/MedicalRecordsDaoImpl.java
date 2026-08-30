package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.MedicalRecordsDao;
import com.pm.medicalwebsite.entity.MedicalRecordsEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.MedicalRecordsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class MedicalRecordsDaoImpl implements MedicalRecordsDao {

    private final MedicalRecordsRepository medicalRecordsRepository;

    @Override
    public MedicalRecordsEntity save(MedicalRecordsEntity medicalRecordsEntity) {
        return medicalRecordsRepository.save(medicalRecordsEntity);
    }

    @Override
    public MedicalRecordsEntity getMedicalRecordsEntitiesByAppointmentId(UUID appointmentId) {
        return medicalRecordsRepository.getMedicalRecordsEntitiesByAppointmentId(appointmentId).orElseThrow(() -> new NotFoundException(ErrorMessages.MEDICAL_RECORDS_NOT_FOUND.getMessage()));
    }

    @Override
    public MedicalRecordsEntity getMedicalRecordsEntitiesById(UUID id) {
        return medicalRecordsRepository.getMedicalRecordsEntitiesById(id).orElseThrow(()-> new NotFoundException(ErrorMessages.MEDICAL_RECORDS_NOT_FOUND.getMessage()));
    }
}
