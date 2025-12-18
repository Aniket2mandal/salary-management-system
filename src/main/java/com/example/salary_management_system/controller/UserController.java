package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
//    @PutMapping("/me")
//    public UserDTO updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
//                                 @RequestBody UserDTO updateDTO) {
//        return userService.updateUser(userDetails.getId(), updateDTO);
//    }
}
