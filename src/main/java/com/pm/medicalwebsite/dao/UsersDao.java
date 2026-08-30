package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface UsersDao {


    Page<UsersEntity> findAll(Specification<UsersEntity> specification, Pageable pageable);

    void deactivateUser(UUID id);

    UsersEntity save(UsersEntity usersEntity);

    UsersEntity findByEmail(String email);

    boolean existsByEmail(String email);

    UsersEntity findById(UUID id);

    boolean existsPhoneNumber(String phoneNumber);

    UsersEntity getUsersEntityByEmail(String email);

    boolean updatePassword(UUID userId,String newPassword);
}
