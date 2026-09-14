package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.Form043Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Form043Repository extends JpaRepository<Form043Entity, UUID> {

    Form043Entity save(Form043Entity form043Entity);

    Optional<Form043Entity> getForm043EntityByUserId(UUID userId);

    Optional<Form043Entity> getForm043EntityById(UUID id);

    boolean existsByUserId(UUID userId);
}
