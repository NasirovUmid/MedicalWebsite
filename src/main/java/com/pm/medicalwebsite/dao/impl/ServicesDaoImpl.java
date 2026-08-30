package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.ServicesDao;
import com.pm.medicalwebsite.entity.ServicesEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.enums.ServicesType;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ServicesDaoImpl implements ServicesDao {

    private final ServicesRepository servicesRepository;

    @Override
    public List<ServicesEntity> findAll() {
        return servicesRepository.findAll();
    }

    @Override
    public ServicesEntity findByServicesType(ServicesType servicesType) {
        return servicesRepository.findByServicesType(servicesType).orElseThrow(RuntimeException::new);
    }

    @Override
    public ServicesEntity save(ServicesEntity servicesEntity) {
        return servicesRepository.save(servicesEntity);
    }

    @Override
    public ServicesEntity findById(UUID id) {
        return servicesRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.SERVICE_NOT_FOUND.getMessage()));
    }

    @Override
    public void deactivateService(UUID id) {
        servicesRepository.deactivateService(id);
    }
}
