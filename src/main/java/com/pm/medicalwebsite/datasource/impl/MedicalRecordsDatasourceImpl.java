package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.MedicalRecordsDao;
import com.pm.medicalwebsite.datasource.MedicalRecordsDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateMedicalRecordsDto;
import com.pm.medicalwebsite.dto.responsedtos.MedicalRecordsResponseDto;
import com.pm.medicalwebsite.entity.MedicalRecordsEntity;
import com.pm.medicalwebsite.mapper.MedicalRecordsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MedicalRecordsDatasourceImpl implements MedicalRecordsDatasource {

    private final MedicalRecordsDao medicalRecordsDao;
    private final MedicalRecordsMapper medicalRecordsMapper;

    @Override
    public MedicalRecordsResponseDto save(CreateMedicalRecordsDto createMedicalRecordsDto) {
        return medicalRecordsMapper.toDto(medicalRecordsDao.save(
                new MedicalRecordsEntity(
                        null,
                        createMedicalRecordsDto.appointmentId(),
                        createMedicalRecordsDto.diagnosis(),
                        createMedicalRecordsDto.treatment(),
                        createMedicalRecordsDto.comment(),
                        null
                )
        ));
    }

    @Override
    public MedicalRecordsResponseDto getMedicalRecordsEntitiesByAppointmentId(UUID appointmentId) {
        return medicalRecordsMapper.toDto(medicalRecordsDao.getMedicalRecordsEntitiesByAppointmentId(appointmentId));
    }

    @Override
    public MedicalRecordsResponseDto getMedicalRecordsEntitiesById(UUID id) {
        return medicalRecordsMapper.toDto(medicalRecordsDao.getMedicalRecordsEntitiesById(id));
    }
}
