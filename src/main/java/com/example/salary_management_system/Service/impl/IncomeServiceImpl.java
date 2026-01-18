package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.IncomeService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.dao.IncomeDao;
import com.example.salary_management_system.dao.SavingFundDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dto.IncomeDTO;
import com.example.salary_management_system.dto.SavingFundDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.IncomeDB;
import com.example.salary_management_system.model.SavingFundDB;
import com.example.salary_management_system.model.UserDB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeDao incomeDao;
    private final UserDao userDao;
    private final SavingFundDao savingFundDao;

    public IncomeServiceImpl(IncomeDao incomeDao, UserDao userDao,SavingFundDao savingFundDao) {
        this.incomeDao = incomeDao;
        this.userDao = userDao;
        this.savingFundDao = savingFundDao;
    }

    @Override
   public IncomeDTO createIncome(IncomeDTO incomeDTO){

        Optional<IncomeDB> incomeDBOptional = incomeDao
                .findIncomeBySourceAndMonthAndUserId(incomeDTO.getSource(),incomeDTO.getMonth(),incomeDTO.getUserId());

        if(!incomeDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_ALREADY_EXISTS+"income");
        }

        Optional<UserDB> userDBOptional=userDao.findUserById(incomeDTO.getUserId());
        if(userDBOptional.isEmpty()){
            throw new BadRequestException("User not found");
        }

        IncomeDB incomeDB = new IncomeDB();
        incomeDB.setSource(incomeDTO.getSource());
        incomeDB.setMonth(incomeDTO.getMonth());
        incomeDB.setAmount(incomeDTO.getAmount());
        incomeDB.setUser(userDBOptional.get());
        incomeDB.setDate(incomeDTO.getDate());
        incomeDao.save(incomeDB);

        incomeDTO.setId(incomeDB.getId());

        return incomeDTO;
    }

    @Override
   public List<IncomeDTO> getIncomeAndSource(Long userId){
        List<IncomeDTO> incomeDTOList = incomeDao.getIncomeByUserId(userId);
        return incomeDTOList;
    }



}
