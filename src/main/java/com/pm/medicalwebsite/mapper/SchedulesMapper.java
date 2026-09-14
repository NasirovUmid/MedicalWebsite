package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.SchedulesResponseDto;
import com.pm.medicalwebsite.entity.SchedulesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulesMapper {

    SchedulesResponseDto toDto(SchedulesEntity schedulesEntity);
}
