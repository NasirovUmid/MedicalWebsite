package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.FilesDao;
import com.pm.medicalwebsite.datasource.FilesDatasource;
import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.entity.FilesEntity;
import com.pm.medicalwebsite.mapper.FilesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilesDatasourceImpl implements FilesDatasource {

    private final FilesDao filesDao;
    private final FilesMapper filesMapper;

    @Override
    public FilesEntity findByFileName(String fileName) {
        return filesDao.findByFileName(fileName);
    }

    @Override
    public FilesResponseDto save(FilesEntity filesEntity) {
        return filesMapper.toDto(filesDao.save(filesEntity));
    }
}
