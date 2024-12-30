package com.example.blog.validation;

import com.example.blog.dto.UserLoginDTO;
import com.example.blog.factory.UserFactory;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.TokenService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserValidation3 {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

	
    @Autowired
    public UserValidation3(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // Check if username already exists
    public void validateUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
    }

    // Check if email already exists
    public void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }
    }
    
    public User validateUsernameAndPassword(UserLoginDTO user) {
        // Check if the username exists
        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());
        
        if (!userFromDb.isPresent()) {
            throw new RuntimeException("Username not found");
        }

        // Validate the password (use password encoder for comparison)
        User dbUser = userFromDb.get();
        
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return dbUser;
    }

}
