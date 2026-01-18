package com.example.salary_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseCategoryDTO {
    private Long id;
    private String name;
    private Long userId;
}
