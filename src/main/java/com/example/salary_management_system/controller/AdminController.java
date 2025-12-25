package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.RoleService;
import com.example.salary_management_system.dto.RoleDTO;
import com.example.salary_management_system.dto.UserRoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;

    @PostMapping("role/create")
    @ResponseBody
    public ResponseEntity<?> createRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.OK);
    }

    @PostMapping("user-role")
    @ResponseBody
    public ResponseEntity<?> createUserRole(@RequestBody UserRoleDTO userRole) {
        return new ResponseEntity<>(roleService.createUserRole(userRole),HttpStatus.OK);
    }
}
