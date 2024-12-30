package com.example.blog.validation;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenValidationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public TokenValidationService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public User validateAndGetUser(String token) {
        if (!tokenService.validateToken(token)) {
            throw new RuntimeException("Invalid or expired token.");
        }

        String username = tokenService.extractUsernameFromToken(token);
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }
}
