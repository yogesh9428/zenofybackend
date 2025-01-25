package com.application.backend.service;

import com.application.backend.model.User;
import com.application.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(User user){
       return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email){
         return userRepository.findByEmail(email);
    }

    public User updatePassword(User user) {
        return userRepository.save(user);
    }
}
