package com.example.blog.factory;

import com.example.blog.dto.UserSignupDTO;
import com.example.blog.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Method to create a new User
    public User createUser(String username, String email, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // Encode password
        return newUser;
    }

	public User createUser(UserSignupDTO userSignupDTO) {
        // Create a new User entity
        User newUser = new User();
        newUser.setUsername(userSignupDTO.getUsername());
        newUser.setEmail(userSignupDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userSignupDTO.getPassword())); // Encode password
        newUser.setName(userSignupDTO.getName());
        newUser.setBio(userSignupDTO.getBio());
        return newUser;
	}
}
