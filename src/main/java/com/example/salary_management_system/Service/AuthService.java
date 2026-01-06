package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.LoginResponse;
import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.enums.RoleType;

public interface AuthService {

    UserDTO registerUser(UserDTO user);
    LoginResponse loginUser(UserDTO user);

}
