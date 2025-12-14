package controller;

import Service.AuthService;
import Service.UserService;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.OK);
    }

}
