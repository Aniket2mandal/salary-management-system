package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.LoginResponse;
import com.example.salary_management_system.dto.UserDTO;

public interface AuthService {

    UserDTO registerUser(UserDTO user);
    LoginResponse loginUser(UserDTO user);

}
