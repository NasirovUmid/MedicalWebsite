package com.pm.medicalwebsite.dto.responsedtos;

import java.util.List;

public record SchedulesListsResponseDto(

        List<SchedulesResponseDto> schedulesResponseDtoList,
        List<SchedulesExceptionsResponseDto> schedulesExceptionsResponseDtoList
) {
}
