package com.pm.medicalwebsite.dto.filters;

import com.pm.medicalwebsite.enums.statuses.UserStatus;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

public record UsersFilterDto(

        @RequestParam(required = false, defaultValue = "fullName")
        String fullName,
        @RequestParam(required = false, defaultValue = "email")
        String email,
        @RequestParam(required = false, name = "phoneNumber") @Pattern(regexp = "^(\\+)?998\\d{9}$")
        String phoneNumber,
        @RequestParam(required = false)
        UserStatus userStatus,
        @RequestParam(required = false,name = "birthdate")
        Instant birthDate
        ) {
}
