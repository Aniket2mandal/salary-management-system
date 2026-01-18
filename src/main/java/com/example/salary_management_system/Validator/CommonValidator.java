package com.example.salary_management_system.Validator;

import com.example.salary_management_system.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CommonValidator {

    private CommonValidator() {}

    public static void validateCredentials(String name,String email,String password) {
        if (name != null ) {
            validateName(name);
        }
        if(email !=null){
            validateEmail(email);
        }
        if(password !=null){
            validatePassword(password);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty() || !Pattern.matches("^[a-zA-Z ]+$", name)) {
            throw new BadRequestException("Invalid name");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty() ||
                !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            throw new BadRequestException("Invalid email");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty() || password.length() < 8) {
            throw new BadRequestException("Invalid password (min 8 characters)");
        }
    }


}
