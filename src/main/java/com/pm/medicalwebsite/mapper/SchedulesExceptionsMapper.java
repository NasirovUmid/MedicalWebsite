package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulesExceptionsMapper {

    SchedulesExceptionsResponseDto toDto(SchedulesExceptionsEntity schedulesExceptionsEntity);
}
