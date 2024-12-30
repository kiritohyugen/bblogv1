package com.example.blog.service;

import com.example.blog.model.User;
import com.example.blog.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtUtil jwtUtil;

    @Autowired
    public TokenService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Generate a token for a user
    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getUsername());
    }

    // Validate the token
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    // Extract the username from the token
    public String extractUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}
