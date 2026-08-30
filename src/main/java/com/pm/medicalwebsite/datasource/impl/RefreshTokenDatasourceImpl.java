package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.RefreshTokenDao;
import com.pm.medicalwebsite.datasource.RefreshTokenDatasource;
import com.pm.medicalwebsite.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RefreshTokenDatasourceImpl implements RefreshTokenDatasource {

    private final RefreshTokenDao refreshTokenDao;

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity refreshTokenEntity) {
        return refreshTokenDao.save(refreshTokenEntity);
    }

    @Override
    public RefreshTokenEntity getRefreshTokenEntityByUserId(UUID userId) {
        return refreshTokenDao.getRefreshTokenEntityByUserId(userId);
    }

    @Override
    public RefreshTokenEntity getRefreshByRefreshToken(String token) {
        return refreshTokenDao.getRefreshByRefreshToken(token);
    }

    @Override
    public void deleteByRefreshToken(String token) {
        refreshTokenDao.deleteByRefreshToken(token);
    }

    @Override
    public void deleteAllByUserId(UUID userId) {
        refreshTokenDao.deleteAllByUserId(userId);
    }

    @Override
    public int deleteExpired() {
        return refreshTokenDao.deleteExpired();
    }
}
