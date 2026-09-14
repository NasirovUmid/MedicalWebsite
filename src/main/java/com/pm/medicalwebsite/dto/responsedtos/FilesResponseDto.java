package com.pm.medicalwebsite.dto.responsedtos;

import com.pm.medicalwebsite.enums.FilePurpose;
import com.pm.medicalwebsite.enums.FileType;

import java.time.Instant;
import java.util.UUID;

public record FilesResponseDto(
        UUID id,
        UUID appointmentId,
        UUID userId,
        String fileName,
        FileType type,
        FilePurpose purpose,
        Integer size,
        UUID uploadedBy,
        Instant uploadedAt
) {
}
