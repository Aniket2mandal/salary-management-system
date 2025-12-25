package com.example.salary_management_system.dto;

import com.example.salary_management_system.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private String message;
    private boolean success;
    private String email;
    private RoleType role;
    private Long userId;
    private String token;
    private String tokenType = "Bearer";
}
