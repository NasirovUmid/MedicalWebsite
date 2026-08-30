package com.pm.medicalwebsite.dto.responsedtos;

import com.pm.medicalwebsite.enums.ServicesType;

import java.util.UUID;

public record ServicesResponseDto(
        UUID id,
        ServicesType servicesType,
        Double price,
        Integer durance
) {
}
