package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.requestdtos.CreateForm043Dto;
import com.pm.medicalwebsite.dto.responsedtos.Form043ResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.Form043Entity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Form043Mapper {

    Form043ResponseDto toDto(Form043Entity form043Entity, UsersResponseDto usersResponseDto);

    Form043Entity toEntity(CreateForm043Dto createForm043Dto);
}
