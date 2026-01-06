package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.SavingFundDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingFundDao extends JpaRepository<SavingFundDB, Long> {
}
