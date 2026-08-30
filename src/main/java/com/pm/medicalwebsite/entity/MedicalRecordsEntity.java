package com.pm.medicalwebsite.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "medical_records")
@Data
public class MedicalRecordsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "appointment_id")
    private UUID appointmentId;

    private String diagnosis;

    private String treatment;

    private String comment;

    @Column(name = "created_At")
    private Instant createdAt;
}
