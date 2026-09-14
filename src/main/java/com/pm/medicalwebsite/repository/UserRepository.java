package com.pm.medicalwebsite.repository;

import com.pm.medicalwebsite.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {

    Page<UsersEntity> findAll(Specification<UsersEntity> specification, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE users SET status = 'DEACTIVATED' WHERE id = :id ", nativeQuery = true)
    void deactivateUser(@Param("id") UUID id);

    Optional<UsersEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    UsersEntity save(UsersEntity usersEntity);

    Optional<UsersEntity> findById(UUID id);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<UsersEntity> getUsersEntityByEmail(String email);

    @Modifying
    @Query(value = "UPDATE users SET password = :newPassword WHERE id = :id", nativeQuery = true)
    boolean updatePassword(@Param("id") UUID userId, @Param("newPassword") String newPassword);
}
