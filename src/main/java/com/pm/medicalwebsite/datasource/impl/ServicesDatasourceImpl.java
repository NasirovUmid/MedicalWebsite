package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.ServicesDao;
import com.pm.medicalwebsite.datasource.ServicesDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateServiceRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.entity.ServicesEntity;
import com.pm.medicalwebsite.enums.ServicesType;
import com.pm.medicalwebsite.enums.statuses.ServicesStatus;
import com.pm.medicalwebsite.mapper.ServicesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ServicesDatasourceImpl implements ServicesDatasource {

    private final ServicesDao servicesDao;
    private final ServicesMapper servicesMapper;

    @Override
    public List<ServicesResponseDto> findAll() {
        return servicesDao.findAll().stream().map(servicesMapper::toDto).toList();
    }

    @Override
    public ServicesResponseDto findByServicesType(ServicesType servicesType) {
        return servicesMapper.toDto(servicesDao.findByServicesType(servicesType));
    }

    @Override
    public ServicesResponseDto save(CreateServiceRequestDto requestDto) {
        return servicesMapper.toDto(servicesDao.save(new ServicesEntity(
                null, requestDto.servicesType(), ServicesStatus.ACTIVE, requestDto.price(), requestDto.durance()
        )));
    }

    @Override
    public ServicesResponseDto getServiceById(UUID id) {
        return servicesMapper.toDto(servicesDao.findById(id));
    }

    @Override
    public void deactivateService(UUID id) {
        servicesDao.deactivateService(id);
    }
}
