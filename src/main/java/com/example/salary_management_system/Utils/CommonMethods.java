package com.example.salary_management_system.Utils;

import com.example.salary_management_system.Service.UserService;
import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component   //To use it's beans or method in other class and if it is not a service we must add component
public class CommonMethods {

    private final UserService userService;

    public CommonMethods(UserService userService) {
        this.userService = userService;
    }

    public Long getAuthenticatedUserId(){
        UserDTO userDTO=userService.getUserInfo();
        if(userDTO==null){
            throw new BadRequestException(HttpStatus.UNAUTHORIZED+" User is not authenticated");
        }
        return userDTO.getId();
    }
}
