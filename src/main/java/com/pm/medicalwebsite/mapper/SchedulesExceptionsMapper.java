package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;
import com.pm.medicalwebsite.entity.SchedulesExceptionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SchedulesExceptionsMapper {

    @Mapping(target = "isDayOff", source = "dayOff")
    SchedulesExceptionsResponseDto toDto(SchedulesExceptionsEntity schedulesExceptionsEntity);
}
