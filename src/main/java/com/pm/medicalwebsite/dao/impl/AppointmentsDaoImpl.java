package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.AppointmentsDao;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.AppointmentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AppointmentsDaoImpl implements AppointmentsDao {

    private final AppointmentsRepository appointmentsRepository;

    @Override
    public Page<AppointmentsEntity> findAll(Specification<AppointmentsEntity> specification, Pageable pageable) {
        return appointmentsRepository.findAll(specification, pageable);
    }

    @Override
    public Page<AppointmentsEntity> getAppointmentsEntitiesByPatientId(UUID patientId, Pageable pageable) {
        return appointmentsRepository.getAppointmentsEntitiesByPatientId(patientId, pageable);
    }

    @Override
    public AppointmentsEntity getUpcomingAppointment(UUID userId) {
        return appointmentsRepository.getUpcomingAppointment(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND.getMessage()));
    }

    @Override
    public void cancelAppointment(UUID id) {
        appointmentsRepository.cancelAppointment(id);
    }

    @Override
    public AppointmentsEntity save(AppointmentsEntity appointmentsEntity) {
        return appointmentsRepository.save(appointmentsEntity);
    }
}
