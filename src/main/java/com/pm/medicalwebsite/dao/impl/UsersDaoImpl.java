package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.UsersDao;
import com.pm.medicalwebsite.entity.UsersEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UsersDaoImpl implements UsersDao {

    private final UserRepository userRepository;

    @Override
    public Page<UsersEntity> findAll(Specification<UsersEntity> specification, Pageable pageable) {
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public void deactivateUser(UUID id) {
        userRepository.deactivateUser(id);
    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        return userRepository.save(usersEntity);
    }

    @Override
    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UsersEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

    @Override
    public boolean existsPhoneNumber(String phoneNumber) {
        return userRepository.existsPhoneNumber(phoneNumber);
    }

    @Override
    public UsersEntity getUsersEntityByEmail(String email) {
        return userRepository.getUsersEntityByEmail(email).orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public boolean updatePassword(UUID userId, String newPassword) {
        return userRepository.updatePassword(userId, newPassword);
    }
}
