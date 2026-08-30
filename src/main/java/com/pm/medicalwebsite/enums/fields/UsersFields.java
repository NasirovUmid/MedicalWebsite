package com.pm.medicalwebsite.enums.fields;

import lombok.Getter;

@Getter
public enum UsersFields {

    FULL_NAME("fullName"),
    EMAIL("email"),
    PHONE_NUMBER("phoneNumber"),
    BIRTHDATE("birthDate"),
    DELETED_AT("deletedAt"),
    CREATED_AT("createdAt");

    private final String field;

    UsersFields(String field) {
        this.field = field;
    }
}
