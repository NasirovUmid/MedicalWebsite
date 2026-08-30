package com.pm.medicalwebsite.dto.requestdtos;

import com.pm.medicalwebsite.enums.ServicesType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateServiceRequestDto(

        @NotNull
        ServicesType servicesType,
        @Positive @Digits(integer = 10, fraction = 2)
        Double price,
        @Positive
        Integer durance
) {
}
