package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    RefreshTokenEntity save(RefreshTokenEntity refreshTokenEntity);

    Optional<RefreshTokenEntity> getRefreshTokenEntityByUserId(UUID userId);

    Optional<RefreshTokenEntity> getRefreshByRefreshToken(String token);

    void deleteByRefreshToken(String token);

    void deleteAllByUserId(UUID userId);

    @Modifying
    @Query(value = "DELETE FROM refresh_token WHERE expiry_date < now()", nativeQuery = true)
    int deleteExpired();
}
