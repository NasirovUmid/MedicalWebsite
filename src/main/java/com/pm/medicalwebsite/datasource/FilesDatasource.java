package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.entity.FilesEntity;

public interface FilesDatasource {

    FilesEntity findByFileName(String fileName);

    FilesEntity save(FilesEntity filesEntity);

}
