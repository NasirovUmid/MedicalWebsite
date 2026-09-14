package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.entity.FilesEntity;

public interface FilesDatasource {

    FilesEntity findByFileName(String fileName);

    FilesResponseDto save(FilesEntity filesEntity);

}
