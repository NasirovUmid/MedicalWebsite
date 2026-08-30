package com.pm.medicalwebsite.specifications;

import com.pm.medicalwebsite.dto.filters.AppointmentsFilterDto;
import com.pm.medicalwebsite.entity.AppointmentsEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsSpecification {

    public static Specification<AppointmentsEntity> build(AppointmentsFilterDto appointmentsFilterDto) {


        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (appointmentsFilterDto.doctorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("doctorId"), appointmentsFilterDto.doctorId()));
            }
            if (appointmentsFilterDto.patientId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("patientId"), appointmentsFilterDto.patientId()));
            }
            if (appointmentsFilterDto.serviceId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serviceId"), appointmentsFilterDto.serviceId()));
            }
            if (appointmentsFilterDto.appointmentDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("appointmentdDate"), appointmentsFilterDto.appointmentDate()));
            }
            if (appointmentsFilterDto.createdAt() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), appointmentsFilterDto.createdAt()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };


    }

}
