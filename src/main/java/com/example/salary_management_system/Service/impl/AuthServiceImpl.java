package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.AuthService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.Validator.CommonValidator;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO registerUser(UserDTO user){

        Optional<UserDB> userDBOptional=userDao.findByEmail(user.getEmail());

        if(userDBOptional.isPresent()){
            throw new BadRequestException(MyConstants.
                    ERR_MSG_ALREADY_EXISTS+ "user with email: "+user.getEmail());
        }

        CommonValidator.validateCredentials(user.getName(),user.getEmail(),user.getPassword());

        UserDB userDB=new UserDB();
        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDao.save(userDB);

        UserDTO userDTO=new UserDTO();
        userDTO.setId(userDB.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
