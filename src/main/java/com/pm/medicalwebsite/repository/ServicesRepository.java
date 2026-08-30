package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.ServicesEntity;
import com.pm.medicalwebsite.enums.ServicesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServicesRepository extends JpaRepository<ServicesEntity, UUID> {

    List<ServicesEntity> findAll();

    Optional<ServicesEntity> findByServicesType(ServicesType servicesType);

    ServicesEntity save(ServicesEntity requestDto);

    Optional<ServicesEntity> findById(UUID id);

    @Modifying
    @Query(value = "UPDATE services SET status = 'DEACTIVATED' WHERE id = :id", nativeQuery = true)
    void deactivateService(UUID id);
}
