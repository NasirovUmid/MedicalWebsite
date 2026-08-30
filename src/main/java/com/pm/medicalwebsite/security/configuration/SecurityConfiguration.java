package com.pm.medicalwebsite.security.configuration;

import com.pm.medicalwebsite.security.jwt.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/**").hasAnyRole("ADMIN", "DOCTOR")

                                .requestMatchers(HttpMethod.POST, "/appointments/**").hasAnyRole("ADMIN", "DOCTOR")
                                .requestMatchers(HttpMethod.GET, "/appointments/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/services/**").hasAnyRole("ADMIN", "DOCTOR")
                                .requestMatchers(HttpMethod.GET, "/services/**").permitAll()

                                .requestMatchers(HttpMethod.POST, "auth/**").authenticated()

                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "DOCTOR"))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
