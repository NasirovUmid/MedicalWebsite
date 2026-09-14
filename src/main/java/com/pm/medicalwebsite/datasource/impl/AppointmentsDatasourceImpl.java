package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.AppointmentsDao;
import com.pm.medicalwebsite.datasource.AppointmentsDatasource;
import com.pm.medicalwebsite.datasource.ServicesDatasource;
import com.pm.medicalwebsite.datasource.UsersDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateAppointmentsRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.AppointmentsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import com.pm.medicalwebsite.mapper.AppointmentsMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AppointmentsDatasourceImpl implements AppointmentsDatasource {

    private final AppointmentsDao appointmentsDao;
    private final UsersDatasource usersDatasource;
    private final ServicesDatasource servicesDatasource;
    private final AppointmentsMapper appointmentsMapper;

    @Override
    public Page<AppointmentsResponseDto> findAll(Specification<AppointmentsEntity> specification, Pageable pageable) {
        return appointmentsDao.findAll(specification, pageable).map(appointmentsMapper::toDto);
    }

    @Override
    public Page<AppointmentsResponseDto> getAppointmentsEntitiesByPatientId(UUID patientId, Pageable pageable) {
        return appointmentsDao.getAppointmentsEntitiesByPatientId(patientId, pageable).map(appointmentsMapper::toDto);
    }

    @Override
    public AppointmentsResponseDto getUpcomingAppointment(UUID usersId) {
        return appointmentsMapper.toDto(appointmentsDao.getUpcomingAppointment(usersId));
    }

    @Override
    public void cancelAppointment(UUID id) {
        appointmentsDao.cancelAppointment(id);
    }

    @Override
    public AppointmentsResponseDto save(CreateAppointmentsRequestDto createAppointmentsRequestDto) {

        UsersResponseDto doctor = usersDatasource.getUserById(createAppointmentsRequestDto.doctorId());
        UsersResponseDto patient = usersDatasource.getUserById(createAppointmentsRequestDto.patientId());
        ServicesResponseDto service = servicesDatasource.getServiceById(createAppointmentsRequestDto.serviceId());

        AppointmentsEntity appointmentsEntity = appointmentsDao.save(new AppointmentsEntity(
                null,
                createAppointmentsRequestDto.doctorId(),
                createAppointmentsRequestDto.patientId(),
                createAppointmentsRequestDto.serviceId(),
                createAppointmentsRequestDto.appointmentDate(),
                createAppointmentsRequestDto.status(),
                Instant.now()));

        return new AppointmentsResponseDto(
                appointmentsEntity.getId(),
                doctor,
                patient,
                service,
                appointmentsEntity.getAppointmentDate(),
                appointmentsEntity.getStatus(),
                appointmentsEntity.getCreatedAt());
    }
}
