package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.RoleDTO;
import com.example.salary_management_system.dto.UserRoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleDTO createRole(RoleDTO role);
    UserRoleDTO createUserRole(UserRoleDTO userRole);
}
