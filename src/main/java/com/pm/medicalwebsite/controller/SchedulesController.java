package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.requestdtos.CreateScheduleExceptionsRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateSchedulesRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesExceptionsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesListsResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.SchedulesResponseDto;
import com.pm.medicalwebsite.usecase.SchedulesUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/schedules")
public class SchedulesController {

    private final SchedulesUseCase schedulesUseCase;

    @PostMapping("/schedules")
    public ResponseEntity<SchedulesResponseDto> createSchedules(@RequestBody CreateSchedulesRequestDto createSchedulesRequestDto) {

        SchedulesResponseDto schedulesResponseDto = schedulesUseCase.createSchedules(createSchedulesRequestDto);

        return ResponseEntity.status(201).body(schedulesResponseDto);
    }

    @PostMapping("/schedules-exceptions")
    public ResponseEntity<SchedulesExceptionsResponseDto> createSchedulesExceptions(@RequestBody CreateScheduleExceptionsRequestDto createScheduleExceptionsRequestDto) {

        SchedulesExceptionsResponseDto schedulesExceptionsResponseDto = schedulesUseCase.createSchedulesExceptions(createScheduleExceptionsRequestDto);

        return ResponseEntity.status(201).body(schedulesExceptionsResponseDto);
    }

    @GetMapping("/{doctorId}")
    public SchedulesListsResponseDto getActualSchedules(@PathVariable(name = "doctorId") UUID doctorId) {

        return schedulesUseCase.getActualSchedules(doctorId);
    }

    @DeleteMapping("/{scheduleId}/schedules")
    public ResponseEntity<Void> deactivateSchedules(@PathVariable(name = "scheduleId") UUID scheduleId) {

        schedulesUseCase.deactivateSchedules(scheduleId);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{scheduleId}/schedules-exceptions")
    public ResponseEntity<Void> deactivateSchedulesExceptions(@PathVariable(name = "scheduleId") UUID scheduleId) {

        schedulesUseCase.deactivateSchedules(scheduleId);

        return ResponseEntity.ok().build();
    }
}
