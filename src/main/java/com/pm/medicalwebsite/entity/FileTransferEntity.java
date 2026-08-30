package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.statuses.FileTransferStatus;
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
@Table(name = "file_transfer")
public class FileTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID fileId;

    private UUID senderId;

    private UUID receiverId;

    private FileTransferStatus status;

    private Instant sentAt;

    private Instant downloadedAt;

    private Instant expiresAt;
}
