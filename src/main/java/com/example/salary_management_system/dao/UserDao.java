package com.example.salary_management_system.dao;

import com.example.salary_management_system.dto.UserDTO;
import com.example.salary_management_system.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserDB, String> {

    Optional<UserDB> findByEmail(String email);
    Optional<UserDB> findUserById(Long userId);

    @Query("SELECT NEW com.example.salary_management_system.dto.UserDTO(u.id, u.name, u.email, r.name) " +
            "FROM UserDB u " +
            "JOIN UserRoleDB ur ON ur.user = u " +  //esma ur.user_id = : u.id kina na gareko
            "JOIN ur.role r " +
            "WHERE u.email = :email")

    Optional<UserDTO> getUserAndRoleByEmail(String email);
}
