package com.hemanth.Roles.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor

@Getter
@Setter
@Data
public class RolesDto
{




        @NotNull
        @NotBlank(message = "Role Name is Required")
        private String roleName;


        @NotBlank(message = "Organisation Name is Required")
        private String orgName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat
    @Future(message = "Please Enter a valid Date")
    private LocalDate createdDate;


        private Boolean roleState;


    @NotNull
    @NotBlank(message = "RoleId is required")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "RoleId must Be AlphaNumerical ")
    private String roleId;


        public RolesDto()
        {

            this.roleState = true;
        }







}
