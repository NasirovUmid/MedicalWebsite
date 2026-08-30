package com.pm.medicalwebsite.security.jwt;

import com.pm.medicalwebsite.dto.responsedtos.JwtAuthenticationResponseDto;
import com.pm.medicalwebsite.enums.TokenTypes;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class JwtUseCase {

    private final Key secretKey;

    public JwtUseCase(@Value("${JWT_SECRET}") String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtAuthenticationResponseDto generateAuthToken(UUID userId, String email) {

        return new JwtAuthenticationResponseDto(
                userId,
                generateAccessToken(email),
                generateRefreshToken(email)
        );
    }

    public String getEmailFromToken(String token) {

        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            return bearerToken.substring(7);

        }
        return null;
    }

    public Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public void validateJwtToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException expiredJwtException) {
            log.error("JWT expired!", expiredJwtException);
            throw new RuntimeException(expiredJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("Unsupported jwt", unsupportedJwtException);
            throw new RuntimeException(unsupportedJwtException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            log.error("Ugly jwt REMAKE!", malformedJwtException);
            throw new RuntimeException(malformedJwtException.getMessage());
        } catch (SecurityException securityException) {
            log.error("Security Exception", securityException);
            throw new RuntimeException(securityException.getMessage());
        } catch (RuntimeException exception) {
            log.error("I dunno ,mb Invalid Token", exception);
            throw new RuntimeException(exception.getMessage());
        }
    }

    private String generateAccessToken(String email) {

        Date date = Date.from(LocalDateTime.now().plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .subject(email)
                .claim("type", TokenTypes.ACCESS)
                .expiration(date)
                .signWith(secretKey)
                .compact();
    }

    private String generateRefreshToken(String email) {

        Date date = Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .subject(email)
                .claim("type", TokenTypes.REFRESH)
                .expiration(date)
                .signWith(secretKey)
                .compact();
    }
}
