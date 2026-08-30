package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.statuses.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class AppointmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID doctorId;

    private UUID patientId;

    private UUID serviceId;

    private Instant appointmentDate;

    private AppointmentStatus status;

    private Instant createdAt;
}
