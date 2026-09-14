package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.requestdtos.ChangePasswordRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.LoginRequestDto;
import com.pm.medicalwebsite.dto.requestdtos.RefreshTokenRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.JwtAuthenticationResponseDto;
import com.pm.medicalwebsite.usecase.AuthUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponseDto> register(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) throws NoSuchAlgorithmException {

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = authUseCase.register(createUserRequestDto);

        return ResponseEntity.status(201).body(jwtAuthenticationResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) throws Exception {


        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = authUseCase.login(loginRequestDto);

        return ResponseEntity.ok().body(jwtAuthenticationResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) throws NoSuchAlgorithmException {

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = authUseCase.refresh(refreshTokenRequestDto);

        return ResponseEntity.ok().body(jwtAuthenticationResponseDto);
    }

    @PostMapping("/change-password")
    public ResponseEntity<JwtAuthenticationResponseDto> changePassword(@Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) throws NoSuchAlgorithmException {

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = authUseCase.changePassword(changePasswordRequestDto);

        return ResponseEntity.ok().body(jwtAuthenticationResponseDto);
    }
}
