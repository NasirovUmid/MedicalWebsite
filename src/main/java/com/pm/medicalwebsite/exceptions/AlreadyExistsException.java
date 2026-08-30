package com.pm.medicalwebsite.exceptions;

import com.pm.medicalwebsite.enums.ErrorMessages;
import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException {

    private final String value;

    public AlreadyExistsException(ErrorMessages errorMessage, String value) {
        super(errorMessage.getMessage());
        this.value = value;
    }
}
