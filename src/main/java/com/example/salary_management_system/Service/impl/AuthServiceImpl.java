package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.AuthService;
import com.example.salary_management_system.Utils.JwtUtils;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.Validator.CommonValidator;
import com.example.salary_management_system.dao.RoleDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dao.UserRoleDao;
import com.example.salary_management_system.dto.LoginResponse;
import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.enums.RoleType;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.RoleDB;
import com.example.salary_management_system.model.UserDB;
import com.example.salary_management_system.model.UserRoleDB;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRoleDao userRoleDao;
    private final RoleDao roleDao;

    @Override
    public UserDTO registerUser(UserDTO user) {

        Optional<UserDB> userDBOptional=userDao.findByEmail(user.getEmail());

        if(userDBOptional.isPresent()){
            throw new BadRequestException(MyConstants.
                    ERR_MSG_ALREADY_EXISTS+ "user with email: "+user.getEmail());
        }

        CommonValidator.validateCredentials(user.getName(),user.getEmail(),user.getPassword());

        UserDB userDB=new UserDB();
        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());
        // Hash the password before saving
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDB.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(userDB);

        Optional<RoleDB> roleDBOptional=roleDao.findByName(user.getRole());

        if(roleDBOptional.isPresent()){
            RoleDB roleDB=roleDBOptional.get();

            UserRoleDB userRoleDB=new UserRoleDB();
            userRoleDB.setUser(userDB);
            userRoleDB.setRole(roleDB);
            userRoleDao.save(userRoleDB);
        }



        UserDTO userDTO=new UserDTO();
        userDTO.setId(userDB.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    @Override
    public LoginResponse loginUser(UserDTO user){
        Optional<UserDB> userDBOptional=userDao.findByEmail(user.getEmail());
        if(userDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"user");
        }
        UserDB userDB=userDBOptional.get();
        if(!passwordEncoder.matches(user.getPassword(),userDB.getPassword())){
            throw new BadRequestException(MyConstants.ERR_MSG_INVALID_CREDENTIALS);
        }

        Optional<UserRoleDB>userRoleDBOptional=userRoleDao.findByUserId(userDB.getId());
        if(userRoleDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"userRole");
        }

        List<RoleType> roleNames = userRoleDBOptional.stream()
                .map(m -> m.getRole().getName())
                .toList();

        String token = jwtUtils.generateToken(userDB.getEmail(), roleNames.getFirst(), userDB.getId());

        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setMessage("Login successful");
        loginResponse.isSuccess();
        loginResponse.setUserId(userDB.getId());
        loginResponse.setEmail(userDB.getEmail());
        loginResponse.setRole(roleNames.getFirst());
        loginResponse.setToken(token);

        return loginResponse;
    }
}
