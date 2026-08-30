package com.pm.medicalwebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "form_043")
@Getter
@Setter
public class Form043Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Instant observationStartDate;

    @Column(nullable = false)
    private Instant observationEndDate;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String studyOrWorkplace;

    @Column(nullable = false)
    private String mainDiagnosis;

    @Column(nullable = false)
    private String concomitantDisease;

    @Column(nullable = false)
    private String allergicHistory;

    @Column(nullable = false)
    private String complaints;

    @Column(nullable = false)
    private String historyOfPresentIllness;

    @Column(nullable = false)
    private String personalHistory;

    @Column(nullable = false)
    private String externalExaminations;

    @Column(nullable = false)
    private String oralCavityAndTeeth;

    @Column(nullable = false)
    private String conditionOfMucosaPeriodontium;

    @Column(nullable = false)
    private String radiologicalAndOtherExaminations;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private String complaintsObjectively;

    @Column(nullable = false)
    private String diagnosisOrTreatment;
}
