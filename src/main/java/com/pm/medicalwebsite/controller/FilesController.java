package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.responsedtos.FilesResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
public class FilesController {


    @PostMapping("/gallery")
    public List<FilesResponseDto> saveGallery(@RequestParam("files") List<MultipartFile> files) {
        return null;
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
