package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.dto.requestdtos.CreateServiceRequestDto;
import com.pm.medicalwebsite.entity.ServicesEntity;
import com.pm.medicalwebsite.enums.ServicesType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface ServicesDao {

    List<ServicesEntity> findAll();

    ServicesEntity findByServicesType(ServicesType servicesType);

    ServicesEntity save(ServicesEntity requestDto);

    ServicesEntity findById(UUID id);

    void deactivateService(UUID id);
}
