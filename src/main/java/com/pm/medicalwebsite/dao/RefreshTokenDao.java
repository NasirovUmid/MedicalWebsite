package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.RefreshTokenEntity;

import java.util.UUID;

public interface RefreshTokenDao {

    RefreshTokenEntity save(RefreshTokenEntity refreshTokenEntity);

    RefreshTokenEntity getRefreshTokenEntityByUserId(UUID userId);

    RefreshTokenEntity getRefreshByRefreshToken(String token);

    void deleteByRefreshToken(String token);

    void deleteAllByUserId(UUID userId);

    int deleteExpired();
}
