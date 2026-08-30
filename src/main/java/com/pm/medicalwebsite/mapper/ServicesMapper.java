package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.entity.ServicesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    ServicesResponseDto toDto(ServicesEntity servicesEntity);

}
