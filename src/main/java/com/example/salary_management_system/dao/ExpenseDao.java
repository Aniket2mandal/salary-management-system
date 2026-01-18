package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.ExpenseDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseDao extends JpaRepository<ExpenseDB, Long> {
}
