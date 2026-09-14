package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.Form043Dao;
import com.pm.medicalwebsite.datasource.Form043Datasource;
import com.pm.medicalwebsite.datasource.UsersDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateForm043Dto;
import com.pm.medicalwebsite.dto.responsedtos.Form043ResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.Form043Entity;
import com.pm.medicalwebsite.mapper.Form043Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class Form043DatasourceImpl implements Form043Datasource {

    private final Form043Dao form043Dao;
    private final Form043Mapper form043Mapper;
    private final UsersDatasource usersDatasource;

    @Override
    public Form043ResponseDto save(CreateForm043Dto createForm043Dto) {

        UsersResponseDto usersResponseDto = usersDatasource.getUserById(createForm043Dto.userId());

        return form043Mapper.toDto(form043Dao.save(form043Mapper.toEntity(createForm043Dto)), usersResponseDto);
    }

    @Override
    public Form043ResponseDto getForm043ByUserId(UUID userId) {

        UsersResponseDto usersResponseDto = usersDatasource.getUserById(userId);

        return form043Mapper.toDto(form043Dao.getForm043ByUserId(userId), usersResponseDto);
    }

    @Override
    public Form043ResponseDto getForm043ById(UUID id) {

        Form043Entity form043Entity = form043Dao.getForm043ById(id);

        UsersResponseDto usersResponseDto = usersDatasource.getUserById(form043Entity.getUserId());

        return form043Mapper.toDto(form043Entity, usersResponseDto);
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return form043Dao.existsByUserId(userId);
    }
}
