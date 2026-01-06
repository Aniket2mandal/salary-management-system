package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.UserService;

import com.example.salary_management_system.dao.RoleDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dao.UserRoleDao;
import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.RoleDB;
import com.example.salary_management_system.model.UserDB;
import com.example.salary_management_system.model.UserRoleDB;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;
    private final RoleDao roleDao;

    @Override
    public UserDTO getUserInfo(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication== null || !authentication.isAuthenticated()){
            throw new BadRequestException(HttpStatus.UNAUTHORIZED+" User is not authenticated");
        }
        //getName() returns the "principal" (the main identifier), which YOU set as the email when creating the JWT token! so it returns email
         String email=authentication.getName();
        Optional<UserDTO> userDTOOptional=userDao.getUserAndRoleByEmail(email);

        if(userDTOOptional.isEmpty()){
        throw new BadRequestException(HttpStatus.NOT_FOUND+" User is not found");
        }

        UserDTO userDTO=userDTOOptional.get();


        //    Optional<UserRoleDB> userRoleDBOptional=userRoleDao.findByUserId(userDB.getId());
        //    if(userRoleDBOptional.isEmpty()){
        //        throw new BadRequestException(HttpStatus.NOT_FOUND+" UserRole is not found");
        //    }
        //    UserRoleDB userRoleDB=userRoleDBOptional.get();
        //    Optional<RoleDB> roleDBOptional=roleDao.findRoleById(userRoleDB.getRoleId());
        //    if(roleDBOptional.isEmpty()){
        //        throw new BadRequestException(HttpStatus.NOT_FOUND+" Role is not found");
        //    }
        //    RoleDB roleDB=roleDBOptional.get();

            return userDTO;
    }



    @Override
    public UserDTO updateUserInfo(UserDTO userDto){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication== null || !authentication.isAuthenticated()){
            throw new BadRequestException(HttpStatus.UNAUTHORIZED+" User is not authenticated");
        }
        String email=authentication.getName();

        if(!userDto.getEmail().equals(email)){
            throw new BadRequestException(HttpStatus.UNAUTHORIZED+"  User is not authenticated");
        }

        Optional<UserDB> userDBOptional=userDao.findByEmail(email);
        if(userDBOptional.isEmpty()){
            throw new BadRequestException(HttpStatus.NOT_FOUND+" User is not found");
        }
            UserDB userDB = userDBOptional.get();
            userDB.setEmail(userDto.getEmail());
            userDB.setName(userDto.getName());
            userDao.save(userDB);

        return userDto;
    }
}
