package com.example.salary_management_system.dto;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingFundDTO {
    private Long id;
    private String name;
    private String amount;
    private String description;
    private Long incomeId;
    private Long userId;
    private LocalDateTime date;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
