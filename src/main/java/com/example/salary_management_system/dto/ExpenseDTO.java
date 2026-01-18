package com.example.salary_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ExpenseDTO {
    private Long id;
    private Long expenseCategoryId;
    private String description;
    private String amount;
    private Long userId;
    private Long incomeId;
    private List<IncomeDTO> income;
    private ExpenseCategoryDTO expenseCategory;

}
