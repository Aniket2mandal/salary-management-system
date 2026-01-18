package com.example.salary_management_system.dao;

import com.example.salary_management_system.dto.IncomeDTO;
import com.example.salary_management_system.model.IncomeDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IncomeDao extends JpaRepository<IncomeDB, Long> {

  Optional<IncomeDB> findIncomeBySourceAndMonthAndUserId(String source, String month, Long userId);

  @Query("SELECT NEW com.example.salary_management_system.dto.IncomeDTO(i.id,i.source,i.amount,i.month,i.userId,i.date) " +
          "FROM IncomeDB i WHERE i.userId = :userId")
  List<IncomeDTO> getIncomeByUserId(Long userId);

  Optional<IncomeDB> findByIdAndUserId(Long incomeId, Long userId);


}
