package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.IncomeDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeDao extends JpaRepository<IncomeDB, Long> {

  List<IncomeDB> findIncomeBySourceAndMonth(String source, String month, Long userId);
}
