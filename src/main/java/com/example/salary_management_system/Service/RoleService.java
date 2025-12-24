package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleDTO createRole(RoleDTO role);
}
