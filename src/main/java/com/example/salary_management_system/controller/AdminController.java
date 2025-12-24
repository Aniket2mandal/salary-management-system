package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.RoleService;
import com.example.salary_management_system.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class AdminController {

    private final RoleService roleService;

    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<?> createRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.OK);
    }
}
