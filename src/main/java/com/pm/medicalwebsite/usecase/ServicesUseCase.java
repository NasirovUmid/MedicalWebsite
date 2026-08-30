package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.ServicesDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateServiceRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.enums.ServicesType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServicesUseCase {

    private final ServicesDatasource servicesDatasource;

    @Transactional
    public ServicesResponseDto createService(CreateServiceRequestDto createServiceRequestDto) {

        return servicesDatasource.save(createServiceRequestDto);
    }

    @Transactional(readOnly = true)
    public List<ServicesResponseDto> getServicesList() {

        return servicesDatasource.findAll();
    }

    public ServicesResponseDto getServicesById(UUID id) {

        return servicesDatasource.getServiceById(id);

    }

    public ServicesResponseDto getServiceByType(ServicesType servicesType) {

        return servicesDatasource.findByServicesType(servicesType);

    }

    @Transactional
    public void deactivateService(UUID id) {

        servicesDatasource.deactivateService(id);

    }
}
