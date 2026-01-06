package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.UserService;
import com.example.salary_management_system.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<?> getUserInfo(){
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.updateUserInfo(user),HttpStatus.OK);
    }

}
