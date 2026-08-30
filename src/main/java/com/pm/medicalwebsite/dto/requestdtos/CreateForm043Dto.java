package com.pm.medicalwebsite.dto.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record CreateForm043Dto(
        @NotNull
        UUID id,
        @NotNull
        UUID userId,
        @NotBlank
        String cardNumber,
        @NotBlank
        Instant observationStartDate,
        @NotBlank
        Instant observationEndDate,
        @NotBlank
        String fullName,
        @NotBlank
        String birthDate,
        @NotBlank
        String address,
        @NotBlank
        String studyOrWorkplace,
        @NotBlank
        String mainDiagnosis,
        @NotBlank
        String concomitantDisease,
        @NotBlank
        String allergicHistory,
        @NotBlank
        String complaints,
        @NotBlank
        String historyOfPresentIllness,
        @NotBlank
        String personalHistory,
        @NotBlank
        String externalExaminations,
        @NotBlank
        String oralCavityAndTeeth,
        @NotBlank
        String conditionOfMucosaPeriodontium,
        @NotBlank
        String radiologicalAndOtherExaminations,
        @NotBlank
        Instant date,
        @NotBlank
        String complaintsObjectively,
        @NotBlank
        String diagnosisOrTreatment
) {
}
