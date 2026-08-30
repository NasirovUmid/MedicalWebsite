package com.pm.medicalwebsite.security.jwt;

import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.security.user.UsersDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUseCase jwtUseCase;
    private final UsersDetailsServiceImpl usersDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String token = jwtUseCase.getTokenFromRequest(request);
        System.out.println("Path: " + request.getServletPath() + " | Token: " + token);

        if (token == null) {
            System.out.println("No token, skipping filter...");
            filterChain.doFilter(request, response);
            return;
        }


        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtUseCase.validateJwtToken(token);

        String username = jwtUseCase.getEmailFromToken(token);
        setCustomUserDetailsToSecurityContextHolder(username);


        filterChain.doFilter(request, response);

    }

    private void setCustomUserDetailsToSecurityContextHolder(String email) {

        UserCustomDetails usersDetails = (UserCustomDetails) usersDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                usersDetails, null, usersDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
