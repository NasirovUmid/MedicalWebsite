package com.pm.medicalwebsite.controller;

import com.pm.medicalwebsite.dto.requestdtos.CreateForm043Dto;
import com.pm.medicalwebsite.dto.responsedtos.Form043ResponseDto;
import com.pm.medicalwebsite.usecase.Form043UseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/form043")
public class Form043Controller {

    private final Form043UseCase form043UseCase;

    @PostMapping
    public ResponseEntity<Form043ResponseDto> saveForm043(@Valid @RequestBody CreateForm043Dto createForm043Dto) {

        Form043ResponseDto form043ResponseDto = form043UseCase.saveForm043(createForm043Dto);

        return ResponseEntity.status(201).body(form043ResponseDto);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<Form043ResponseDto> getForm043ByUserId(@PathVariable(name = "userId") UUID userId) {

        return ResponseEntity.ok().body(form043UseCase.getForm043ByUserId(userId));
    }

    @GetMapping("/{form043Id}/form043")
    public ResponseEntity<Form043ResponseDto> getForm043ById(@PathVariable(name = "form043Id") UUID id) {

        return ResponseEntity.ok().body(form043UseCase.getForm043ById(id));
    }

    @GetMapping("/check-out/{userId}")
    public ResponseEntity<Boolean> checkOut(@PathVariable(name = "userId") UUID userId) {

        Boolean exists = form043UseCase.checkOut(userId);

        return ResponseEntity.ok().body(exists);
    }
}
