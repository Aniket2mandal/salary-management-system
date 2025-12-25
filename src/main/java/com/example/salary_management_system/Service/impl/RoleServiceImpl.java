package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.RoleService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.dao.RoleDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dao.UserRoleDao;
import com.example.salary_management_system.dto.RoleDTO;
import com.example.salary_management_system.dto.UserRoleDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.RoleDB;
import com.example.salary_management_system.model.UserDB;
import com.example.salary_management_system.model.UserRoleDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final UserRoleDao userRoleDao;
    private final UserDao userDao;

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

    @Override
   public UserRoleDTO createUserRole(UserRoleDTO userRole){
        Optional<UserRoleDB> userRoleDBOptional =userRoleDao
                .findByUserIdAndRoleId(userRole.getUserId(),userRole.getRoleId());

        if(userRoleDBOptional.isPresent()){
            throw new BadRequestException(MyConstants.ERR_MSG_ALREADY_CONNECTED+"userRole");
        }

        Optional<UserDB>userDBOptional =userDao.findUserById(userRole.getUserId());
        if(userDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"user");
        }

        Optional<RoleDB>roleDBOptional =roleDao.findRoleById(userRole.getRoleId());
        if(roleDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"role");
        }

        UserRoleDB userRoleDB= new UserRoleDB();
        userRoleDB.setUser(userDBOptional.get());
        userRoleDB.setRole(roleDBOptional.get());
        userRoleDao.save(userRoleDB);

        userRole.setId(userRoleDB.getId());

        return userRole;
    }
}
