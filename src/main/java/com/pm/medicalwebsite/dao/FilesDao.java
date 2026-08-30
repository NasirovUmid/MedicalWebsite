package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.FilesEntity;

public interface FilesDao {

    FilesEntity findByFileName(String fileName);

    FilesEntity save(FilesEntity filesEntity);
}
