package com.pm.medicalwebsite.security.user;

import com.example.generated.enums.UserRole;
import com.pm.medicalwebsite.entity.UsersEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserCustomDetails implements UserDetails {

    private final UsersEntity usersEntity;

    private final SimpleGrantedAuthority userRole;

    public UserCustomDetails(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
        this.userRole = new SimpleGrantedAuthority(
                "ROLE_" + usersEntity.getRole().name());
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return List.of(userRole);
    }

    @Override
    public @Nullable String getPassword() {
        return usersEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return usersEntity.getEmail();
    }
}
