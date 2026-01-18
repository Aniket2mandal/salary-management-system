package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.IncomeService;
import com.example.salary_management_system.Service.UserService;
import com.example.salary_management_system.Utils.CommonMethods;
import com.example.salary_management_system.dto.IncomeDTO;
import com.example.salary_management_system.dto.SavingFundDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;
    private final UserService userService;
    private final CommonMethods commonMethods;

    public IncomeController(IncomeService incomeService, UserService userService, CommonMethods commonMethods) {
        this.incomeService = incomeService;
        this.userService = userService;
        this.commonMethods = commonMethods;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createIncome(@RequestBody IncomeDTO incomeDTO) {
        //SET AUTHENTICATED USERID INCOME INCOME DTO
        Long userId = commonMethods.getAuthenticatedUserId();
        incomeDTO.setUserId(userId);
        return new ResponseEntity<>(incomeService.createIncome(incomeDTO), HttpStatus.OK);
    }

    @GetMapping("/IncomeAndSource")
    @ResponseBody
    public ResponseEntity<?> getIncomeAndSource() {
        Long userId = commonMethods.getAuthenticatedUserId();
        return new ResponseEntity<>(incomeService.getIncomeAndSource(userId),HttpStatus.OK);
    }


}
