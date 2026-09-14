package com.pm.medicalwebsite.mapper;

import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.entity.FilesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilesMapper {

    FilesResponseDto toDto(FilesEntity filesEntity);
}
