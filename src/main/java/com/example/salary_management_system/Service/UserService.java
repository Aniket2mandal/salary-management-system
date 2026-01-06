package com.example.salary_management_system.Service;


import com.example.salary_management_system.dto.UserDTO;

public interface UserService {

    UserDTO getUserInfo();
    UserDTO updateUserInfo(UserDTO userDTO);

}
