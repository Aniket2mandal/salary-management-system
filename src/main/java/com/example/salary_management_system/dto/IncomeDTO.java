package com.example.salary_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeDTO {

    public IncomeDTO(Long id, String source, String amount, String month, Long userId, LocalDateTime date) {
        this.id = id;
        this.source = source;
        this.amount = amount;
        this.month = month;
        this.userId = userId;
        this.date = date;
    }

    private Long id;

    private String source;

    private String amount;

    private String month;

    private Long userId;

    private LocalDateTime date;

}
