package com.application.backend.controller;

import com.application.backend.dto.ForgotPasswordDto;
import com.application.backend.dto.SignInDTO;
import com.application.backend.model.User;
import com.application.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){
        if (userService.findByEmail(user.getEmail()).isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with this email already exists");
        User save = userService.signup(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody SignInDTO sign){
        Optional<User> user = userService.findByEmail(sign.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(sign.getPassword())){
            return ResponseEntity.ok("Sign In Successfull");
        }
        return ResponseEntity.status(401).body("Invalid Email or Password");

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgetpassword(@RequestBody ForgotPasswordDto forget){
        Optional<User> user = userService.findByEmail(forget.getEmail());
        if (user.isPresent()){
            User update = user.get();
            update.setPassword(forget.getNew_password());
            userService.updatePassword(update);
            return ResponseEntity.ok("Password reset Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email does not exists");
    }
}
