package com.pm.medicalwebsite.enums.fields;

import lombok.Getter;

@Getter
public enum AppointmentsFields {

    DOCTOR_ID("doctor_id"),
    PATIENT_ID("patientId"),
    SERVICE_ID("serviceId"),
    APPOINTMENTS_DATE("appointmentDate"),
    CREATED_AT("createdAt");

    private final String field;

    AppointmentsFields(String field) {
        this.field = field;
    }
}
