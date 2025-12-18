package com.example.salary_management_system.dao;

import com.example.salary_management_system.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserDB, String> {

    Optional<UserDB> findByEmail(String email);
    Optional<UserDB> findUserById(Long id);
}
