package Service.impl;

import Service.AuthService;
import Utils.MyConstants;
import dao.UserDao;
import dto.UserDTO;
import exception.BadRequestException;
import model.UserDB;
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
