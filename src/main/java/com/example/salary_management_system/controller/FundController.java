package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.FundService;
import com.example.salary_management_system.Utils.CommonMethods;
import com.example.salary_management_system.dto.ExpenseDTO;
import com.example.salary_management_system.dto.SavingFundDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fund")
@RequiredArgsConstructor
public class FundController {

    private final CommonMethods commonMethods;
    private final FundService fundService;

    @PostMapping("/savingFund")
    @ResponseBody
    public ResponseEntity<?> handleSavingFund(@RequestBody SavingFundDTO savingFundDTO) {
        Long userId = commonMethods.getAuthenticatedUserId();
        savingFundDTO.setUserId(userId);
        return new ResponseEntity<>(fundService.handleSavingFund(savingFundDTO), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    @ResponseBody
    public ResponseEntity<?> handleExpenses(@RequestBody ExpenseDTO expenseDTO) {
        Long userId = commonMethods.getAuthenticatedUserId();
        expenseDTO.setUserId(userId);
        return new ResponseEntity<>(fundService.handleExpenses(expenseDTO), HttpStatus.OK);
    }
}
