package com.pm.medicalwebsite.dao;

import com.pm.medicalwebsite.entity.Form043Entity;

import java.util.UUID;

public interface Form043Dao {

    Form043Entity save(Form043Entity form043Entity);

    Form043Entity getForm043ByUserId(UUID userId);

    Form043Entity getForm043ById(UUID id);

    boolean existsByUserId(UUID userId);
}
