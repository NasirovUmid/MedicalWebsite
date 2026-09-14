package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.UsersRoleTypes;
import com.pm.medicalwebsite.enums.statuses.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(name = "birthdate")
    private Instant birthDate;

    @Column(name = "avatar_id", nullable = true)
    private UUID avatarId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private UsersRoleTypes role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private UserStatus userStatus = UserStatus.ACTIVE;

    private Instant deletedAt;

    private Instant createdAt;
}
