package com.pm.medicalwebsite.datasource;

import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


public interface UsersDatasource {

    boolean existsByEmail(String email);

    UsersEntity save(CreateUserRequestDto userRequestDto);

    UsersEntity save(UsersEntity usersEntity);

    Page<UsersResponseDto> getUsersPage(Specification<UsersEntity> specification, Pageable pageable);

    UsersResponseDto getUserById(UUID id);

    void deactivateUser(UUID id);

    boolean existsPhoneNumber(String phoneNumber);

    UsersEntity getUsersEntityByEmail(String email);

    boolean updatePassword(UUID userId, String newPassword);
}
