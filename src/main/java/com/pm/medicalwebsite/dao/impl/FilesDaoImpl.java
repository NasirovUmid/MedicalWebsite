package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.FilesDao;
import com.pm.medicalwebsite.entity.FilesEntity;
import com.pm.medicalwebsite.repository.FilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FilesDaoImpl implements FilesDao {

    private final FilesRepository filesRepository;

    @Override
    public FilesEntity findByFileName(String fileName) {
        return filesRepository.findByFileName(fileName).orElseThrow(RuntimeException::new);
    }

    @Override
    public FilesEntity save(FilesEntity filesEntity) {
        return filesRepository.save(filesEntity);
    }
}
