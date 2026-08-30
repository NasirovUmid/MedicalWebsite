package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateMedicalRecordsDto;
import com.pm.medicalwebsite.dto.responsedtos.MedicalRecordsResponseDto;

import java.util.UUID;

public interface MedicalRecordsDatasource {

    MedicalRecordsResponseDto save(CreateMedicalRecordsDto createMedicalRecordsDto);

    MedicalRecordsResponseDto getMedicalRecordsEntitiesByAppointmentId(UUID appointmentId);

    MedicalRecordsResponseDto getMedicalRecordsEntitiesById(UUID id);

}
