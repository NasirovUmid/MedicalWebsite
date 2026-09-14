package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.filters.UsersFilterDto;
import com.pm.medicalwebsite.dto.requestdtos.CreateUserRequestDto;
import com.pm.medicalwebsite.dto.responsedtos.UsersResponseDto;
import com.pm.medicalwebsite.security.user.UserCustomDetails;
import com.pm.medicalwebsite.usecase.UsersUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UsersResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto userRequestDto) throws IOException {

        UsersResponseDto usersResponseDto = usersUseCase.createUser(userRequestDto);

        return ResponseEntity.status(201).body(usersResponseDto);
    }

    @GetMapping
    public Page<UsersResponseDto> getUserPage(@RequestParam(defaultValue = "0", name = "page") int page,
                                              @RequestParam(defaultValue = "10", name = "size") int size,
                                              @RequestParam(defaultValue = "fullName,asc", name = "sort") String sort,
                                              @Valid @ModelAttribute UsersFilterDto usersFilterDto) {

        System.out.println("asdfdsafdsgfdsgnkfdjhgkdsbgkjdshkgjdshlkgjdskhdslkhlgdsihlgdsh----- " + page + "   SIZE  " + size);

        return usersUseCase.getUsersPage(page, size, sort, usersFilterDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUser(@PathVariable(name = "id") UUID id) {

        UsersResponseDto usersResponseDto = usersUseCase.getUserById(id);

        System.out.println("USER = "+usersResponseDto.toString());

        return ResponseEntity.ok().body(usersResponseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/me")
    public ResponseEntity<UsersResponseDto> getCurrentUser(@AuthenticationPrincipal UserCustomDetails userCustomDetails) throws BadRequestException {

        UsersResponseDto usersResponseDto = usersUseCase.getCurrentUser(userCustomDetails);

        return ResponseEntity.ok().body(usersResponseDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable(name = "id") UUID id) {
        usersUseCase.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
}
