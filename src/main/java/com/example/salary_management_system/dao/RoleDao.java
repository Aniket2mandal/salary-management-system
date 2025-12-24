package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.RoleDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<RoleDB, Long> {

    Optional<RoleDB> findByName(String roleName);
}
