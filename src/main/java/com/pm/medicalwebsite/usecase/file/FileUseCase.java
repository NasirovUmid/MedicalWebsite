package com.pm.medicalwebsite.usecase.file;

import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileUseCase {

    FilesResponseDto saveAvatar(MultipartFile avatar, UserCustomDetails userCustomDetails) throws IOException;

    List<FilesResponseDto> saveGalleryPhoto(List<MultipartFile> files, UUID userId) throws IOException;

    List<FilesResponseDto> saveAlignerFiles(List<MultipartFile> files, UUID userId);

    List<FilesResponseDto> saveBracerFiles(List<MultipartFile> files, UUID userId);

    FilesResponseDto saveTreatmentPlan(MultipartFile file, UUID userId);

    Resource getFile(UUID fileId);

    void deleteFile(UUID id);
}
