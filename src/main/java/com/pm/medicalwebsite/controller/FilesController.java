package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.usecase.file.FileUseCase;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FilesController {

    private final FileUseCase fileUseCase;

    @PostMapping("/gallery")
    public List<FilesResponseDto> saveGallery(@RequestParam("files") List<MultipartFile> files) {
        return null;
    }


    @PostMapping("/avatar")
    public ResponseEntity<FilesResponseDto> saveAvatar(@RequestParam("avatar") MultipartFile file,
                                                       @AuthenticationPrincipal UserCustomDetails userCustomDetails) throws IOException {
        FilesResponseDto filesResponseDto = fileUseCase.saveAvatar(file, userCustomDetails);

        return ResponseEntity.status(201).body(filesResponseDto);
    }

    @PostMapping("/treatment-plan")
    public FilesResponseDto saveTreatmentPlan(@RequestParam("file") MultipartFile file) {

        return null;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable(name = "id") UUID id) {

        return null;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable(name = "id") UUID id) {


        return ResponseEntity.ok().build();

    }
}
