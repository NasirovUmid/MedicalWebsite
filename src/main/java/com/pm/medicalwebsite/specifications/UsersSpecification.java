package com.pm.medicalwebsite.specifications;

import com.pm.medicalwebsite.dto.filters.UsersFilterDto;
import com.pm.medicalwebsite.entity.UsersEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UsersSpecification {

    public static Specification<UsersEntity> build(UsersFilterDto usersFilterDto) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (usersFilterDto.fullName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + usersFilterDto.fullName().toLowerCase() + "%"));
            }
            if (usersFilterDto.email() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), usersFilterDto.email()));
            }
            if (usersFilterDto.phoneNumber() != null) {
                predicates.add(criteriaBuilder.like(root.get("phoneNUmber"), "%" + usersFilterDto.phoneNumber() + "%"));
            }
            if (usersFilterDto.userStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("userStatus"), usersFilterDto.userStatus()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
