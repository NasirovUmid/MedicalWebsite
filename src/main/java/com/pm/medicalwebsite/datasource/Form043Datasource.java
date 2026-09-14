package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateForm043Dto;
import com.pm.medicalwebsite.dto.responsedtos.Form043ResponseDto;

import java.util.UUID;

public interface Form043Datasource {

    Form043ResponseDto save(CreateForm043Dto createForm043Dto);

    Form043ResponseDto getForm043ByUserId(UUID userId);

    Form043ResponseDto getForm043ById(UUID id);

    boolean existsByUserId(UUID userId);
}
