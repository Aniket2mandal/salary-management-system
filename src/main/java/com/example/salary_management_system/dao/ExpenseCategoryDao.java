package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.ExpenseCategoryDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseCategoryDao extends JpaRepository<ExpenseCategoryDB, Long> {

    Optional<ExpenseCategoryDB> findExpenseCategoryById(Long expenseCategoryId);
}
