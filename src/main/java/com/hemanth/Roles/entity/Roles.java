package com.hemanth.Roles.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Data
public class Roles
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID rid;

    String roleName;

    String orgName;


    LocalDate createdDate;


    Boolean roleState;


    String roleId;
}
