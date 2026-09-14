package com.pm.medicalwebsite.dao.impl;

import com.pm.medicalwebsite.dao.Form043Dao;
import com.pm.medicalwebsite.entity.Form043Entity;
import com.pm.medicalwebsite.enums.ErrorMessages;
import com.pm.medicalwebsite.exceptions.NotFoundException;
import com.pm.medicalwebsite.repository.Form043Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class Form043DaoImpl implements Form043Dao {

    private final Form043Repository form043Repository;

    @Override
    public Form043Entity save(Form043Entity form043Entity) {
        return form043Repository.save(form043Entity);
    }

    @Override
    public Form043Entity getForm043ByUserId(UUID userId) {
        return form043Repository.getForm043EntityByUserId(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.FORM_043_NOT_FOUND.getMessage()));
    }

    @Override
    public Form043Entity getForm043ById(UUID id) {
        return form043Repository.getForm043EntityById(id).orElseThrow(() -> new NotFoundException(ErrorMessages.FORM_043_NOT_FOUND.getMessage()));
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return form043Repository.existsByUserId(userId);
    }
}
