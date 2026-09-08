package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateAppointmentsRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.AppointmentsResponseDto;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface AppointmentsDatasource {

    Page<AppointmentsResponseDto> findAll(Specification<AppointmentsEntity> specification, Pageable pageable);

    Page<AppointmentsResponseDto> getAppointmentsEntitiesByPatientId(UUID patientId, Pageable pageable);

    void cancelAppointment(UUID id);

    AppointmentsResponseDto save(CreateAppointmentsRequestDto createAppointmentsRequestDto);

    AppointmentsResponseDto getUpcomingAppointment(UUID usersId);
}
