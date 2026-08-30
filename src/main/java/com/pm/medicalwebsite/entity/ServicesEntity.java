package com.pm.medicalwebsite.entity;

import com.pm.medicalwebsite.enums.statuses.ServicesStatus;
import com.pm.medicalwebsite.enums.ServicesType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private ServicesType servicesType;

    private ServicesStatus status;

    private Double price;

    private Integer durance;

}
