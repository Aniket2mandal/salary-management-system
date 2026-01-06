package com.example.salary_management_system.Service;

import com.example.salary_management_system.dto.IncomeDTO;
import com.example.salary_management_system.dto.SavingFundDTO;

import java.util.List;

public interface IncomeService {
    IncomeDTO createIncome(IncomeDTO incomeDTO);
    List<IncomeDTO> getIncomeAndSource(Long userId);
    SavingFundDTO handleSavingFund(SavingFundDTO savingFundDTO);
}
