package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.FilePurpose;
import com.pm.medicalwebsite.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class FilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fileName;

    private String storagePath;

    private FileType type;

    private FilePurpose purpose;

    private Integer size;

    private UUID uploadedBy;

    private Instant uploadedAt;
}
