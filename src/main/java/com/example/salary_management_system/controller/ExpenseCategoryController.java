package com.example.salary_management_system.controller;

import com.example.salary_management_system.Service.ExpenseCategoryService;
import com.example.salary_management_system.Utils.CommonMethods;
import com.example.salary_management_system.dto.ExpenseCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class ExpenseCategoryController {

    private final CommonMethods commonMethods;
    private final ExpenseCategoryService expenseCategoryService;

    @PostMapping("/handle-category")
    @ResponseBody
    public ResponseEntity<?> handleCategory(@RequestBody ExpenseCategoryDTO categoryDTO) {
        Long userId = commonMethods.getAuthenticatedUserId();
        categoryDTO.setUserId(userId);
        return new ResponseEntity<>(expenseCategoryService.handleCategory(categoryDTO), HttpStatus.OK);
    }
}
