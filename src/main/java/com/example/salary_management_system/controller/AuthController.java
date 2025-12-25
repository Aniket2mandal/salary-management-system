package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.AuthService;
import com.example.salary_management_system.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(authService.loginUser(userDTO), HttpStatus.OK);
    }

}
