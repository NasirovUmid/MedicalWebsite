package com.pm.medicalwebsite.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    USER_NOT_FOUND("The user was not found"),
    SERVICE_NOT_FOUND("The service was not found"),
    FILE_NOR_FOUND("The file was nor found"),
    APPOINTMENT_NOT_FOUND("The appointment was not found"),
    MEDICAL_RECORDS_NOT_FOUND("The medical record was not found"),
    REFRESH_TOKEN_NOT_FOUND("The refreshToken was not found"),
    FORM_043_NOT_FOUND("The form043 was not found"),

    USER_IS_DEACTIVATED("The User is Deactivated so action cant be done"),

    USER_ALREADY_EXISTS("The email already exists"),

    WRONG_CREDENTIALS("The Credentials are Wrong");


    private final String message;


    ErrorMessages(String message) {
        this.message = message;
    }
}
