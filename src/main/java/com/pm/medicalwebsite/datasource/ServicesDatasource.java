package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateServiceRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.enums.ServicesType;

import java.util.List;
import java.util.UUID;

public interface ServicesDatasource {


    List<ServicesResponseDto> findAll();

    ServicesResponseDto findByServicesType(ServicesType servicesType);

    ServicesResponseDto save(CreateServiceRequestDto requestDto);

    ServicesResponseDto getServiceById(UUID id);

    void deactivateService(UUID id);
}
