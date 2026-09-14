package com.pm.medicalwebsite.dto.responsedtos;

import com.pm.medicalwebsite.enums.statuses.UserStatus;
import com.pm.medicalwebsite.enums.UsersRoleTypes;

import java.time.Instant;
import java.util.UUID;

public record UsersResponseDto(
        UUID id,
        String fullName,
        String email,
        String phoneNumber,
        Instant birthDate,
        UUID avatarId,
        UsersRoleTypes role,
        UserStatus userStatus,
        Instant deletedAt,
        Instant createdAt
) {
}
