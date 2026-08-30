package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.filters.AppointmentsFilterDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateAppointmentsRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateMedicalRecordsDto;
import com.pm.medicalwebsite.dto.responsedtos.AppointmentsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.MedicalRecordsResponseDto;
import com.pm.medicalwebsite.usecase.AppointmentsUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentsController {

    private final AppointmentsUseCase appointmentsUseCase;

    @PostMapping
    public ResponseEntity<AppointmentsResponseDto> createAppointments(@RequestBody CreateAppointmentsRequestDto createAppointmentsRequestDto) {

        AppointmentsResponseDto appointmentsResponseDto = appointmentsUseCase.createAppointment(createAppointmentsRequestDto);

        return ResponseEntity.status(201).body(appointmentsResponseDto);
    }

    @PostMapping("/medical-records")
    public ResponseEntity<MedicalRecordsResponseDto> createMedicalRecords(@RequestBody CreateMedicalRecordsDto createMedicalRecordsDto) {

        MedicalRecordsResponseDto medicalRecordsResponseDto = appointmentsUseCase.createMedicalRecords(createMedicalRecordsDto);

        return ResponseEntity.status(201).body(medicalRecordsResponseDto);
    }

    @GetMapping("/medical-records/{id}")
    public ResponseEntity<MedicalRecordsResponseDto> getMedicalRecordsById(@PathVariable(name = "id") UUID id) {

        MedicalRecordsResponseDto medicalRecordsResponseDto = appointmentsUseCase.getMedicalRecordsById(id);

        return ResponseEntity.ok().body(medicalRecordsResponseDto);
    }

    @GetMapping("/appointments/{id}/medical-records")
    public ResponseEntity<MedicalRecordsResponseDto> getMedicalRecordsByAppointmentId(@PathVariable(name = "id") UUID appointmentId) {

        MedicalRecordsResponseDto medicalRecordsResponseDto = appointmentsUseCase.getMedicalRecordsByAppointmentId(appointmentId);

        return ResponseEntity.ok().body(medicalRecordsResponseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public Page<AppointmentsResponseDto> getAppointmentsPage(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "createdAt,asc") String sort,
            @Valid @ModelAttribute AppointmentsFilterDto appointmentsFilterDto
    ) {

        return appointmentsUseCase.getAppointmentsPage(size, page, sort, appointmentsFilterDto);
    }

    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR', 'ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable(name = "id") UUID id) {

        appointmentsUseCase.cancelAppointment(id);

        return ResponseEntity.ok().build();
    }

}
