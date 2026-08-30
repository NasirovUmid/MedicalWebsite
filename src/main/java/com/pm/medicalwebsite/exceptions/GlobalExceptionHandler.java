package com.pm.medicalwebsite.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException, HttpServletRequest request) {

        log.error("Not found = {} ", alreadyExistsException.getMessage());

        return ResponseEntity.status(409).body(ApiProblem.of(HttpStatus.CONFLICT, alreadyExistsException.getMessage(), request, alreadyExistsException,
                null, alreadyExistsException.getValue())
        );
    }


}
