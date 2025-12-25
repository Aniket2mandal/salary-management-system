package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.UserRoleDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleDao extends JpaRepository <UserRoleDB,Long> {
    Optional<UserRoleDB> findByUserId(Long userId);
    Optional<UserRoleDB> findByUserIdAndRoleId(Long userId,Long roleId);
}
