package com.example.salary_management_system.Service.impl;

import com.example.salary_management_system.Service.FundService;
import com.example.salary_management_system.Utils.MyConstants;
import com.example.salary_management_system.dao.ExpenseCategoryDao;
import com.example.salary_management_system.dao.ExpenseDao;
import com.example.salary_management_system.dao.IncomeDao;
import com.example.salary_management_system.dao.SavingFundDao;
import com.example.salary_management_system.dao.UserDao;
import com.example.salary_management_system.dto.ExpenseDTO;
import com.example.salary_management_system.dto.IncomeDTO;
import com.example.salary_management_system.dto.SavingFundDTO;
import com.example.salary_management_system.exception.BadRequestException;
import com.example.salary_management_system.model.ExpenseCategoryDB;
import com.example.salary_management_system.model.ExpenseDB;
import com.example.salary_management_system.model.IncomeDB;
import com.example.salary_management_system.model.SavingFundDB;
import com.example.salary_management_system.model.UserDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {

    private final UserDao userDao;
    private final SavingFundDao savingFundDao;
    private final IncomeDao incomeDao;
    private final ExpenseCategoryDao expenseCategoryDao;
    private final ExpenseDao expenseDao;

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

    @Override
    public ExpenseDTO handleExpenses(ExpenseDTO expenseDTO){

        Optional<ExpenseCategoryDB> expenseCategoryDBOptional=expenseCategoryDao
                .findExpenseCategoryById(expenseDTO.getExpenseCategoryId());

        if(expenseCategoryDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"expense category");
        }
        ExpenseCategoryDB expenseCategoryDB = expenseCategoryDBOptional.get();

        Optional<IncomeDB> incomeDBOptional=incomeDao.findByIdAndUserId(expenseDTO.getIncomeId(),expenseDTO.getUserId());

        if(incomeDBOptional.isEmpty()){
            throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"income");
        }

        IncomeDB incomeDB = incomeDBOptional.get();

        ExpenseDB expenseDB;
        if(expenseDTO.getId() != null){
            Optional<ExpenseDB> expenseDBOptional=expenseDao.findById(expenseDTO.getId());
            if(expenseDBOptional.isEmpty()){
                throw new BadRequestException(MyConstants.ERR_MSG_NOT_FOUND+"expense");
            }
           expenseDB = expenseDBOptional.get();
        }else{
            expenseDB = new ExpenseDB();
        }
            expenseDB.setDescription(expenseDTO.getDescription());
            expenseDB.setExpenseCategory(expenseCategoryDB);
            expenseDB.setIncome(incomeDB);
            expenseDB.setAmount(expenseDTO.getAmount());
            expenseDao.save(expenseDB);

            expenseDTO.setId(expenseDB.getId());

        return expenseDTO;
    }
}
