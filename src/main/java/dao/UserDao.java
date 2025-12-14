package dao;

import model.UserDB;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserDB, String> {

    Optional<UserDB> findByEmail(String email);
}
