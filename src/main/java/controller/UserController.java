package controller;

import Service.UserService;
import dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
//    @PutMapping("/me")
//    public UserDTO updateProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
//                                 @RequestBody UserDTO updateDTO) {
//        return userService.updateUser(userDetails.getId(), updateDTO);
//    }
}
