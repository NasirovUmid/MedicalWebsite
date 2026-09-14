package com.pm.medicalwebsite.usecase.file.impl;

import com.pm.medicalwebsite.datasource.FilesDatasource;
import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.entity.FilesEntity;
import com.pm.medicalwebsite.enums.FilePurpose;
import com.pm.medicalwebsite.enums.FileType;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.usecase.file.FileUseCase;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUseCaseImpl implements FileUseCase {

    @Value("${storageLocation}")
    private String storageUrl;

    private FilesDatasource filesDatasource;

    @Override
    public FilesResponseDto saveAvatar(MultipartFile avatar, UserCustomDetails userCustomDetails) throws IOException {

        return saveFile(avatar, null, userCustomDetails.getUsersEntity().getId(), FilePurpose.AVATAR);
    }

    @Override
    public List<FilesResponseDto> saveGalleryPhoto(List<MultipartFile> files, UUID userId) throws IOException {

        List<FilesResponseDto> galleryPhotos = new ArrayList<>();

        for (MultipartFile file : files) {

            galleryPhotos.add(saveFile(file, null, userId, FilePurpose.GALLERY));

        }
        return galleryPhotos;
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

    private FilesResponseDto saveFile(MultipartFile multipartFile, UUID appointmentId, UUID userId, FilePurpose filePurpose) throws IOException {

        if (multipartFile.isEmpty()) {
            throw new BadRequestException();
        }

        LocalDate today = LocalDate.now();
        Path root = Paths.get(storageUrl);


        Path directory = root
                .resolve(String.valueOf(today.getYear()))
                .resolve(String.format("%02d", today.getMonthValue()))
                .resolve(String.format("%02d", today.getDayOfMonth()));

        FilesResponseDto filesResponseDto;

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        UserCustomDetails userCustomDetails =
                (UserCustomDetails) authentication.getPrincipal();

        UUID uploader = userCustomDetails.getUsersEntity().getId();

        String fileName = extractExt(multipartFile);

        FileType fileType = resolveFileType(multipartFile);

        try {

            Files.createDirectories(directory);

            Files.copy(multipartFile.getInputStream(), directory.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            filesResponseDto = filesDatasource.save(new FilesEntity(null, appointmentId, userId, fileName, directory.toString(), fileType, filePurpose, (int) multipartFile.getSize(), uploader, Instant.now()));
        } catch (IOException e) {

            removePhysicallyFile(fileName);

            throw new IOException(e);
        }

        return filesResponseDto;

    }

    private FileType resolveFileType(MultipartFile file) throws BadRequestException {

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        if (extension == null) {
            throw new BadRequestException();
        }

        return switch (extension.toLowerCase()) {

            case "pdf" -> FileType.PDF;
            case "jpg", "jpeg", "png", "webp" -> FileType.IMAGE;
            case "zip", "rar", "7z" -> FileType.ARCHIVE;
            case "stl", "obj", "ply", "3mf" -> FileType.MODEL_3D;
            case "doc", "docx", "ppt", "pptx", "xls", "xlsx" -> FileType.DOCUMENT;
            default -> throw new BadRequestException();
        };

    }
}
