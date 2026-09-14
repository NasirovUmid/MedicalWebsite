package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.RefreshTokenDatasource;
import com.pm.medicalwebsite.datasource.UsersDatasource;
import com.pm.medicalwebsite.dto.requestdtos.ChangePasswordRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.LoginRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.RefreshTokenRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.JwtAuthenticationResponseDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.RefreshTokenEntity;
import com.pm.medicalwebsite.entity.UsersEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.enums.TokenTypes;
import com.pm.medicalwebsite.enums.statuses.UserStatus;
import com.pm.medicalwebsite.exceptions.AlreadyExistsException;
import com.pm.medicalwebsite.exceptions.BadCredentialsException;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.security.jwt.JwtUseCase;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthUseCase {

    private final UsersDatasource usersDatasource;
    private final JwtUseCase jwtUseCase;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenDatasource refreshTokenDatasource;
    private final static Duration DURATION_OF_REFRESH_TOKEN = Duration.ofDays(2);

    @Transactional
    public JwtAuthenticationResponseDto register(CreateUserRequestDto createUserRequestDto) throws NoSuchAlgorithmException {

        if (usersDatasource.existsByEmail(createUserRequestDto.email()) || usersDatasource.existsPhoneNumber(createUserRequestDto.phoneNumber())) {
            throw new AlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS, createUserRequestDto.email());
        }

        UsersResponseDto usersResponseDto = usersDatasource.save(createUserRequestDto);

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = jwtUseCase.generateAuthToken(usersResponseDto.id(), usersResponseDto.email());

        refreshTokenDatasource.save(RefreshTokenEntity.builder()
                .userId(usersResponseDto.id())
                .refreshToken(hashing(jwtAuthenticationResponseDto.refreshToken()))
                .expiryDate(Instant.now().plus(DURATION_OF_REFRESH_TOKEN))
                .build());

        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto login(LoginRequestDto loginRequestDto) throws Exception {

        UsersEntity usersEntity = usersDatasource.getUsersEntityByEmail(loginRequestDto.email());

        if (usersEntity == null) {
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage());
        }
        if (usersEntity.getUserStatus() == UserStatus.DEACTIVATED) {
            throw new BadRequestException(ErrorMessages.USER_IS_DEACTIVATED.getMessage());
        }

        if (!passwordEncoder.matches(loginRequestDto.password(), usersEntity.getPassword())) {
            throw new BadCredentialsException(ErrorMessages.WRONG_CREDENTIALS, usersEntity.getEmail());
        }

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = jwtUseCase.generateAuthToken(usersEntity.getId(), usersEntity.getEmail());

        refreshTokenDatasource.save(RefreshTokenEntity.builder()
                .userId(usersEntity.getId())
                .refreshToken(hashing(jwtAuthenticationResponseDto.refreshToken()))
                .expiryDate(Instant.now().plus(DURATION_OF_REFRESH_TOKEN))
                .build());

        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto refresh(RefreshTokenRequestDto refreshTokenRequestDto) throws NoSuchAlgorithmException {

        RefreshTokenEntity refreshTokenEntity = refreshTokenDatasource.getRefreshByRefreshToken(hashing(refreshTokenRequestDto.refreshToken()));


        if (refreshTokenEntity == null) {
            throw new NotFoundException(ErrorMessages.REFRESH_TOKEN_NOT_FOUND.getMessage());
        }

        if (!jwtUseCase.extractClaims(refreshTokenRequestDto.refreshToken()).get("type").equals(TokenTypes.REFRESH.name())) {
            throw new IllegalStateException();
        }

        jwtUseCase.validateJwtToken(refreshTokenRequestDto.refreshToken());

        String email = jwtUseCase.getEmailFromToken(refreshTokenRequestDto.refreshToken());

        refreshTokenDatasource.deleteByRefreshToken(hashing(refreshTokenRequestDto.refreshToken()));

        UUID userId = usersDatasource.getUsersEntityByEmail(email).getId();

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = jwtUseCase.generateAuthToken(userId, email);

        refreshTokenDatasource.save(RefreshTokenEntity.builder()
                .id(refreshTokenEntity.getId())
                .userId(refreshTokenEntity.getUserId())
                .refreshToken(hashing(jwtAuthenticationResponseDto.refreshToken()))
                .expiryDate(Instant.now().plus(DURATION_OF_REFRESH_TOKEN))
                .build());

        return jwtAuthenticationResponseDto;
    }

    @Transactional
    public JwtAuthenticationResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) throws NoSuchAlgorithmException {

        if (changePasswordRequestDto.oldPassword().equals(changePasswordRequestDto.newPassword())) {
            throw new IllegalStateException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserCustomDetails user)) {
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage());
        }

        UsersEntity usersEntity = usersDatasource.getUsersEntityByEmail(user.getUsername());

        if (!passwordEncoder.matches(changePasswordRequestDto.oldPassword(), usersEntity.getPassword())) {
            throw new BadCredentialsException(ErrorMessages.WRONG_CREDENTIALS, usersEntity.getEmail());
        }

        boolean isChanged = usersDatasource.updatePassword(usersEntity.getId(), passwordEncoder.encode(changePasswordRequestDto.newPassword()));

        if (!isChanged) {
            throw new RuntimeException();
        }
        refreshTokenDatasource.deleteAllByUserId(usersEntity.getId());
        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = jwtUseCase.generateAuthToken(usersEntity.getId(), usersEntity.getEmail());

        refreshTokenDatasource.save(RefreshTokenEntity.builder()
                .userId(usersEntity.getId())
                .refreshToken(hashing(jwtAuthenticationResponseDto.refreshToken()))
                .expiryDate(Instant.now().plus(DURATION_OF_REFRESH_TOKEN))
                .build());

        return jwtAuthenticationResponseDto;
    }

    private String hashing(String refreshToken) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(refreshToken.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}
