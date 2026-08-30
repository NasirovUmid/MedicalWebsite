package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.RefreshTokenDao;
import com.pm.medicalwebsite.entity.RefreshTokenEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RefreshTokenDaoImpl implements RefreshTokenDao {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity refreshTokenEntity) {
        return refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    public RefreshTokenEntity getRefreshTokenEntityByUserId(UUID userId) {
        return refreshTokenRepository.getRefreshTokenEntityByUserId(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.REFRESH_TOKEN_NOT_FOUND.getMessage()));
    }

    @Override
    public RefreshTokenEntity getRefreshByRefreshToken(String token) {
        return refreshTokenRepository.getRefreshByRefreshToken(token).orElseThrow(() -> new NotFoundException(ErrorMessages.REFRESH_TOKEN_NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteByRefreshToken(String token) {
        refreshTokenRepository.deleteByRefreshToken(token);
    }

    @Override
    public void deleteAllByUserId(UUID userId) {
        refreshTokenRepository.deleteAllByUserId(userId);
    }

    @Override
    public int deleteExpired() {
        return refreshTokenRepository.deleteExpired();
    }
}
