package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.filters.UsersFilterDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.usecase.UsersUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersUseCase usersUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsersResponseDto> createUser(@Valid
                                                       @RequestPart("data") CreateUserRequestDto userRequestDto,
                                                       @RequestPart(value = "avatar", required = false) MultipartFile avatar) throws IOException {

        UsersResponseDto usersResponseDto = usersUseCase.createUser(userRequestDto, avatar);

        return ResponseEntity.status(201).body(usersResponseDto);
    }

    @GetMapping
    public Page<UsersResponseDto> getUserPage(@RequestParam(required = false, defaultValue = "0") int page,
                                              @RequestParam(required = false, defaultValue = "10") int size,
                                              @RequestParam(required = false, defaultValue = "fullName,asc") String sort,
                                              @Valid @ModelAttribute UsersFilterDto usersFilterDto) {

        return usersUseCase.getUsersPage(page, size, sort, usersFilterDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUser(@PathVariable(name = "id") UUID id) {

        UsersResponseDto usersResponseDto = usersUseCase.getUserById(id);

        return ResponseEntity.ok().body(usersResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable(name = "id") UUID id) {
        usersUseCase.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
}
