package com.pm.medicalwebsite.datasource.impl;

import com.pm.medicalwebsite.dao.UsersDao;
import com.pm.medicalwebsite.datasource.UsersDatasource;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.UsersEntity;
import com.pm.medicalwebsite.mapper.UsersMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UsersDatasourceImpl implements UsersDatasource {

    private final UsersDao usersDao;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByEmail(String email) {
        return usersDao.existsByEmail(email);
    }

    @Override
    public Page<UsersResponseDto> getUsersPage(Specification<UsersEntity> specification, Pageable pageable) {
        return usersDao.findAll(specification, pageable).map(usersMapper::toDto);
    }

    @Override
    public UsersResponseDto getUserById(UUID id) {
        return usersMapper.toDto(usersDao.findById(id));
    }

    @Override
    public void deactivateUser(UUID id) {
        usersDao.deactivateUser(id);
    }

    @Override
    public boolean existsPhoneNumber(String phoneNumber) {
        return usersDao.existsPhoneNumber(phoneNumber);
    }

    @Override
    public UsersEntity getUsersEntityByEmail(String email) {
        return usersDao.getUsersEntityByEmail(email);
    }

    @Override
    public boolean updatePassword(UUID userId, String newPassword) {
        return usersDao.updatePassword(userId, newPassword);
    }

    @Override
    public UsersEntity save(CreateUserRequestDto userRequestDto) {

        return usersDao.save(new UsersEntity(null, userRequestDto.fullName(), userRequestDto.email(), passwordEncoder.encode(userRequestDto.password()),
                userRequestDto.phoneNumber(), userRequestDto.birthDate(), null, userRequestDto.role(), userRequestDto.status(), null, null));
    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        return usersDao.save(usersEntity);
    }

}
