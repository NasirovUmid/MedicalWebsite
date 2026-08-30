package com.pm.medicalwebsite.dto.responsedtos;

import java.time.Instant;
import java.util.UUID;

public record Form043ResponseDto(

        UUID id,

        UsersResponseDto user,

        String cardNumber,

        Instant observationStartDate,

        Instant observationEndDate,

        String fullName,

        String birthDate,

        String address,

        String studyOrWorkplace,

        String mainDiagnosis,

        String concomitantDisease,

        String allergicHistory,

        String complaints,

        String historyOfPresentIllness,

        String personalHistory,

        String externalExaminations,

        String oralCavityAndTeeth,

        String conditionOfMucosaPeriodontium,

        String radiologicalAndOtherExaminations,

        Instant date,

        String complaintsObjectively,

        String diagnosisOrTreatment

) {
}
