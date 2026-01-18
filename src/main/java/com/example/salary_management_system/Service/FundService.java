package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.ExpenseDTO;
import com.example.salary_management_system.dto.SavingFundDTO;

public interface FundService {
    SavingFundDTO handleSavingFund(SavingFundDTO savingFundDTO);
    ExpenseDTO handleExpenses(ExpenseDTO expenseDTO);
}
