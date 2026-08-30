package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.statuses.UserStatus;
import com.pm.medicalwebsite.enums.UsersRoleTypes;
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
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fullName;

    private String email;

    private String password;

    private String phoneNumber;

    private Instant birthDate;

    @Column(name = "avatar_id", nullable = true)
    private UUID avatarId;

    @Column(name = "user_role")
    private UsersRoleTypes role;

    @Column(name = "user_status")
    private UserStatus userStatus = UserStatus.ACTIVE;

    private Instant deletedAt;

    private Instant createdAt;
}
