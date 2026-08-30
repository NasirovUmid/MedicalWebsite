package com.pm.medicalwebsite.usecase.file.impl;

import com.pm.medicalwebsite.datasource.FilesDatasource;
import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.entity.FilesEntity;
import com.pm.medicalwebsite.enums.FilePurpose;
import com.pm.medicalwebsite.enums.FileType;
import com.pm.medicalwebsite.usecase.file.FileUseCase;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class FileUseCaseImpl implements FileUseCase {

    @Value("${storageLocation}")
    private String storageUrl;

    private FilesDatasource filesDatasource;

    @Override
    public UUID saveAvatar(MultipartFile avatar, UUID uploader) throws IOException {

        if (avatar.isEmpty()) {
            return null;
        }

        LocalDate today = LocalDate.now();
        Path root = Paths.get(storageUrl);


        Path directory = root
                .resolve(String.valueOf(today.getYear()))
                .resolve(String.format("%02d", today.getMonthValue()))
                .resolve(String.format("%02d", today.getDayOfMonth()));

        Files.createDirectories(directory);

        String fileName = extractExt(avatar);

        Files.copy(avatar.getInputStream(), directory.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        FilesEntity filesEntity = filesDatasource.save(new FilesEntity(null, fileName, directory.toString(), FileType.IMAGE, FilePurpose.AVATAR, (int) avatar.getSize(), uploader, Instant.now()));

        return filesEntity.getId();
    }

    @Override
    public List<FilesResponseDto> saveGalleryPhoto(List<MultipartFile> files, UUID userId) {
        return null;
    }

    @Override
    public List<FilesResponseDto> saveAlignerFiles(List<MultipartFile> files, UUID userId) {
        return List.of();
    }

    @Override
    public List<FilesResponseDto> saveBracerFiles(List<MultipartFile> files, UUID userId) {
        return List.of();
    }

    @Override
    public FilesResponseDto saveTreatmentPlan(MultipartFile file, UUID userId) {
        return null;
    }

    @Override
    public Resource getFile(UUID fileId) {
        return null;
    }

    @Override
    public void deleteFile(UUID id) {

    }

    private String extractExt(MultipartFile file) {

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        return UUID.randomUUID() + "." + extension;
    }

    private void removePhysicallyFile(String fileName) throws IOException {

        Path path = Paths.get(storageUrl + fileName);

        Files.deleteIfExists(path);

    }

    private FilesResponseDto save() {
    return null;
    }

    private FileType resolveFileType(MultipartFile file) throws BadRequestException {

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        if (extension == null) {
            throw new BadRequestException();
        }

        return switch (extension.toLowerCase()) {

            case "pdf" -> FileType.PDF;
            case "jpg", "jpeg" -> FileType.IMAGE;
            case "stl" -> FileType.STL;
            default -> throw new BadRequestException();
        };

    }
}
