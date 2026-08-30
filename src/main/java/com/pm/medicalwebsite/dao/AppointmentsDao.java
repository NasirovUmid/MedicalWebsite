package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.AppointmentsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface AppointmentsDao {

    Page<AppointmentsEntity> findAll(Specification<AppointmentsEntity> specification, Pageable pageable);


    void cancelAppointment(UUID id);

    AppointmentsEntity save(AppointmentsEntity appointmentsEntity);
}
