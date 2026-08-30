package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.requestdtos.CreateServiceRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.ServicesResponseDto;
import com.pm.medicalwebsite.usecase.ServicesUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServicesController {

    private final ServicesUseCase servicesUseCase;

    @GetMapping
    public List<ServicesResponseDto> getServicesList() {

        return servicesUseCase.getServicesList();

    }

    @PostMapping
    public ResponseEntity<ServicesResponseDto> createService(@Valid @RequestBody CreateServiceRequestDto createServiceRequestDto) {

        ServicesResponseDto servicesResponseDto = servicesUseCase.createService(createServiceRequestDto);

        return ResponseEntity.status(201).body(servicesResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesResponseDto> getServiceById(@PathVariable(name = "id") UUID id) {

        ServicesResponseDto servicesResponseDto = servicesUseCase.getServicesById(id);

        return ResponseEntity.ok().body(servicesResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateService(@PathVariable(name = "id") UUID id) {

        servicesUseCase.deactivateService(id);

        return ResponseEntity.ok().build();
    }
}
