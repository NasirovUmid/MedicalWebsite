package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(source = "usersEntity.birthDate", target = "birthDate")
    @Mapping(source = "usersEntity.userStatus", target = "userStatus")
    UsersResponseDto toDto(UsersEntity usersEntity);

}
