package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.FileTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileTransferRepository extends JpaRepository<FileTransferEntity, UUID> {
}
