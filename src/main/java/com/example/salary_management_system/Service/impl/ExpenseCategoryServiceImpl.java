package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.ExpenseCategoryService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.Validator.CommonValidator;
import com.example.salary_management_system.dao.ExpenseCategoryDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dto.ExpenseCategoryDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.ExpenseCategoryDB;
import com.example.salary_management_system.model.UserDB;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    private final CommonValidator commonValidator;
    private final ExpenseCategoryDao expenseCategoryDao;
    private final UserDao userDao;

    public ExpenseCategoryDTO handleCategory(ExpenseCategoryDTO categoryDTO){
        commonValidator.validateName(categoryDTO.getName());

        ExpenseCategoryDB expenseCategoryDB=null;

        if(categoryDTO.getId() != null){
            Optional<ExpenseCategoryDB> expenseCategoryDBOptional = expenseCategoryDao.findById(categoryDTO.getId());
            if(expenseCategoryDBOptional.isEmpty()){
              throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+ "Category");
            }
             expenseCategoryDB = expenseCategoryDBOptional.get();
        }else{
             expenseCategoryDB = new ExpenseCategoryDB();
        }

        Optional<UserDB> userDBOptional = userDao.findUserById(categoryDTO.getUserId());
        if(userDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+ "User");
        }
        UserDB userDB = userDBOptional.get();


        expenseCategoryDB.setName(categoryDTO.getName());
        expenseCategoryDB.setUser(userDB);
        //ADD EXPENSE ALSO
        expenseCategoryDao.save(expenseCategoryDB);

        categoryDTO.setId(expenseCategoryDB.getId());

        return categoryDTO;
    }

}
