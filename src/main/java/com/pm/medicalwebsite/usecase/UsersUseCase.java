package com.pm.medicalwebsite.usecase;

import com.pm.medicalwebsite.datasource.UsersDatasource;
import com.pm.medicalwebsite.dto.filters.UsersFilterDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.entity.UsersEntity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.enums.fields.UsersFields;
import com.pm.medicalwebsite.exceptions.AlreadyExistsException;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.specifications.UsersSpecification;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsersUseCase {

    private final UsersDatasource usersDatasource;

    public UsersResponseDto createUser(CreateUserRequestDto userRequestDto) {

        if (usersDatasource.existsByEmail(userRequestDto.email())) {
            throw new AlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS, userRequestDto.email());
        }

        if (usersDatasource.existsPhoneNumber(userRequestDto.phoneNumber())) {
            throw new AlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS, userRequestDto.phoneNumber());

        }

        return usersDatasource.save(userRequestDto);

    }

    public Page<UsersResponseDto> getUsersPage(int page, int size, String sort, @Valid UsersFilterDto usersFilterDto) {

        Specification<UsersEntity> specification = UsersSpecification.build(usersFilterDto);

        Sort sorted = sortingUsers(sort);

        return usersDatasource.getUsersPage(specification, PageRequest.of(page, size, sorted));
    }

    public UsersResponseDto getUserById(UUID id) {
        return usersDatasource.getUserById(id);
    }

    private Sort sortingUsers(String sort) {

        if (sort == null || sort.isBlank()) {
            return Sort.by("fullName").ascending();
        }
        String[] parts = sort.split(",");

        String field = parts[0];

        boolean allowed = Arrays.stream(UsersFields.values())
                .anyMatch(value -> value.getField().equals(field));

        if (!allowed) {
            return Sort.by("fullName").ascending();
        }

        Sort.Direction direction = Sort.Direction.ASC;

        if (parts.length > 1 &&
                "desc".equalsIgnoreCase(parts[1])) {
            direction = Sort.Direction.DESC;
        }

        return Sort.by(direction, field);
    }

    public void deactivateUser(UUID id) {
        usersDatasource.deactivateUser(id);
    }

    public UsersResponseDto getCurrentUser(UserCustomDetails userCustomDetails) throws BadRequestException {

        if (userCustomDetails == null) {
            throw new BadRequestException();
        }

        return new UsersResponseDto(
                userCustomDetails.getUsersEntity().getId(),
                userCustomDetails.getUsersEntity().getFullName(),
                userCustomDetails.getUsersEntity().getEmail(),
                userCustomDetails.getUsersEntity().getPhoneNumber(),
                userCustomDetails.getUsersEntity().getBirthDate(),
                userCustomDetails.getUsersEntity().getAvatarId(),
                userCustomDetails.getUsersEntity().getRole(),
                userCustomDetails.getUsersEntity().getUserStatus(),
                userCustomDetails.getUsersEntity().getDeletedAt(),
                userCustomDetails.getUsersEntity().getCreatedAt()
        );
    }
}
