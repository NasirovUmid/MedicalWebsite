package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.FilesDao;
import com.pm.medicalwebsite.datasource.FilesDatasource;
import com.pm.medicalwebsite.entity.FilesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilesDatasourceImpl implements FilesDatasource {

    private final FilesDao filesDao;

    @Override
    public FilesEntity findByFileName(String fileName) {
        return filesDao.findByFileName(fileName);
    }

    @Override
    public FilesEntity save(FilesEntity filesEntity) {
        return filesDao.save(filesEntity);
    }
}
