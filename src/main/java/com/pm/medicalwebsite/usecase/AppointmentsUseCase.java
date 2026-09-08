package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.AppointmentsDatasource;
import com.pm.medicalwebsite.datasource.MedicalRecordsDatasource;
import com.pm.medicalwebsite.dto.filters.AppointmentsFilterDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateAppointmentsRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateMedicalRecordsDto;
import com.pm.medicalwebsite.dto.responsedtos.AppointmentsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.MedicalRecordsResponseDto;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import com.pm.medicalwebsite.entity.UsersEntity;
import com.pm.medicalwebsite.enums.fields.AppointmentsFields;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.specifications.AppointmentsSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppointmentsUseCase {

    private final AppointmentsDatasource appointmentsDatasource;
    private final MedicalRecordsDatasource medicalRecordsDatasource;

    public AppointmentsResponseDto createAppointment(CreateAppointmentsRequestDto requestDto) {

        return appointmentsDatasource.save(requestDto);

    }

    public Page<AppointmentsResponseDto> getAppointmentsPage(int size, int page, String sort, AppointmentsFilterDto appointmentsFilterDto) {

        Specification<AppointmentsEntity> specification = AppointmentsSpecification.build(appointmentsFilterDto);

        Sort newSort = sortingAppointments(sort);

        Pageable pageable = PageRequest.of(page, size, newSort);

        return appointmentsDatasource.findAll(specification, pageable);
    }

    public MedicalRecordsResponseDto createMedicalRecords(CreateMedicalRecordsDto createMedicalRecordsDto) {
        return medicalRecordsDatasource.save(createMedicalRecordsDto);
    }

    public MedicalRecordsResponseDto getMedicalRecordsByAppointmentId(UUID appointmentId) {
        return medicalRecordsDatasource.getMedicalRecordsEntitiesByAppointmentId(appointmentId);
    }

    public MedicalRecordsResponseDto getMedicalRecordsById(UUID id) {
        return medicalRecordsDatasource.getMedicalRecordsEntitiesById(id);
    }

    private Sort sortingAppointments(String sort) {

        if (sort == null || sort.isBlank()) {
            return Sort.by("createdAt").ascending();
        }

        String[] parts = sort.split(",");

        String field = parts[0];

        boolean allowed = Arrays.stream(AppointmentsFields.values()).
                anyMatch(values -> values.getField().equals(field));

        if (!allowed) {
            return Sort.by("createdAt").ascending();
        }

        Sort.Direction direction = Sort.Direction.ASC;

        if (parts.length > 1 &&
                "desc".equalsIgnoreCase(parts[1])) {
            direction = Sort.Direction.DESC;
        }

        return Sort.by(direction, field);
    }

    public void cancelAppointment(UUID id) {
        appointmentsDatasource.cancelAppointment(id);
    }

    public Page<AppointmentsResponseDto> getPersonalAppointments(UserCustomDetails userCustomDetails, int page, int size) {

        UUID userId = userCustomDetails.getUsersEntity().getId();
        Pageable pageable = PageRequest.of(page, size);

        return appointmentsDatasource.getAppointmentsEntitiesByPatientId(userId, pageable);
    }

    public AppointmentsResponseDto getUpcomingAppointment(UserCustomDetails userCustomDetails) {
        return appointmentsDatasource.getUpcomingAppointment(userCustomDetails.getUsersEntity().getId());
    }
}
