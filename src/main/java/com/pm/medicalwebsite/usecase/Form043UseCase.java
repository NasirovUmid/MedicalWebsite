package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.Form043Datasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateForm043Dto;
import com.pm.medicalwebsite.dto.responsedtos.Form043ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class Form043UseCase {

    private final Form043Datasource form043Datasource;

    public Form043ResponseDto saveForm043(CreateForm043Dto createForm043Dto) {

        return form043Datasource.save(createForm043Dto);
    }

    public Form043ResponseDto getForm043ByUserId(UUID userId) {

        return form043Datasource.getForm043ByUserId(userId);
    }

    public Form043ResponseDto getForm043ById(UUID id) {

        return form043Datasource.getForm043ById(id);
    }

    public Boolean checkOut(UUID userId) {

        return form043Datasource.existsByUserId(userId);

    }
}
