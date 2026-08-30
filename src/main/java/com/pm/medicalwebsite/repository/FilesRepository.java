package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, UUID> {

    Optional<FilesEntity> findByFileName(String fileName);

    FilesEntity save(FilesEntity filesEntity);

}
