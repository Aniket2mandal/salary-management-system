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

    @Override
    public SavingFundDTO handleSavingFund(SavingFundDTO savingFundDTO){

        Optional<UserDB> userDBOptional=userDao.findUserById(savingFundDTO.getUserId());
        if(userDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"user");
        }
        UserDB userDB = userDBOptional.get();

        Optional<IncomeDB> incomeDBOptional=incomeDao.findByIdAndUserId(savingFundDTO.getIncomeId(),savingFundDTO.getUserId());
        if(incomeDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"income");
        }
        IncomeDB incomeDB = incomeDBOptional.get();

        SavingFundDB savingFundDB;

         if(savingFundDTO.getId() != null){
             Optional<SavingFundDB> savingFundDBOptional=savingFundDao.findById(savingFundDTO.getId());
             if(savingFundDBOptional.isEmpty()){
                 throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"savingFund");
             }
            savingFundDB = savingFundDBOptional.get();
         }else{
             savingFundDB = new SavingFundDB();
         }

        savingFundDB.setName(savingFundDTO.getName());
        savingFundDB.setAmount(savingFundDTO.getAmount());
        savingFundDB.setDescription(savingFundDTO.getDescription());
        savingFundDB.setDate(savingFundDTO.getDate());
        savingFundDB.setUser(userDB);
        savingFundDB.setIncome(incomeDB);
        savingFundDao.save(savingFundDB);

        savingFundDTO.setId(savingFundDB.getId());

       return savingFundDTO;
    }


}
