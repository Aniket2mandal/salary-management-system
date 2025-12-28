package com.example.salary_management_system.dto;

import com.example.salary_management_system.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    public UserDTO(Long id, String name, String email,RoleType role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role=role;
    }

    private Long id;
    private String name;
    private String email;
    private String password;

//    extras
    private RoleType role;
}
