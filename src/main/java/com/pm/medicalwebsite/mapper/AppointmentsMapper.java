package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.AppointmentsResponseDto;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentsMapper {

    AppointmentsResponseDto toDto(AppointmentsEntity appointmentsEntity);
}
