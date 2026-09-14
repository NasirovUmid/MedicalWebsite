package com.pm.medicalwebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules_entity")
public class SchedulesExceptionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID doctorId;

    private LocalDate exceptionDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isDayOff;
}
