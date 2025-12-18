package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.IncomeService;
import com.example.salary_management_system.dto.IncomeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> createIncome(@RequestBody IncomeDTO incomeDTO) {
        //SET AUTHENTICATED USERID INCOME INCOMEDTO

        return new ResponseEntity<>(incomeService.createIncome(incomeDTO), HttpStatus.OK);
    }
}
