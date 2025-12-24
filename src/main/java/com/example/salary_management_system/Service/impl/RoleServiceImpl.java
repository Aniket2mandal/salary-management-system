package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.RoleService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.dao.RoleDao;
import com.example.salary_management_system.dto.RoleDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.RoleDB;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {

        Optional<RoleDB> roleDBOptional=roleDao.findByName(roleDTO.getName());
        if(roleDBOptional.isPresent()){
            throw new BadRequestException(MyConstants.ERR_MSG_ALREADY_EXISTS+"role");
        }
        RoleDB roleDB= new RoleDB();
        roleDB.setName(roleDTO.getName());
        roleDao.save(roleDB);

        roleDTO.setId(roleDB.getId());
        return roleDTO;
    }
}
