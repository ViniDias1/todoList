package com.ignis.to_do.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ignis.to_do.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public boolean validateUser(String name, String email, String password) {
        return userRepository.existsByNameAndEmailAndPassword(name, email, password);
    }  
    
}
