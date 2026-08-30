package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.MedicalRecordsResponseDto;
import com.pm.medicalwebsite.entity.MedicalRecordsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalRecordsMapper {

    MedicalRecordsResponseDto toDto(MedicalRecordsEntity medicalRecordsEntity);

}
