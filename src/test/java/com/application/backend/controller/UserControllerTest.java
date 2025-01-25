package com.application.backend.controller;

import com.application.backend.model.User;
import com.application.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
    }
    @Test
    public void SignUp_Success() {
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userService.signup(user)).thenReturn(user);

        ResponseEntity<?> response = userController.signUp(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void SignUp_Failure() {
        when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.signUp(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User with this email already exists", response.getBody());
    }
}

