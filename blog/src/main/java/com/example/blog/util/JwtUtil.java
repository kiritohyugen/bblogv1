package com.example.blog.util;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.JwtException;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

	 private SecretKey key;

	    // Initialize the key from environment variable after bean is created
	    @PostConstruct
	    public void init() {
	        
	        String secretKey = System.getenv("JWT_SECRET_KEY");  // Get the key from environment variable
	        if (secretKey == null) {
	            throw new RuntimeException("JWT_SECRET_KEY environment variable not set.");
	        }
	        key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512"); // Create key from the secret string
	    }
	    
//	SecretKey key = Jwts.SIG.HS512.key().build();

	// Generate a JWT token
	public String generateToken(String username) {

		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).signWith(key).compact();

	}

	// Validate JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(key).build().parse(token);

			return true;
		} catch (JwtException ex) {
			return false;

		}
	}

	// Extract username from JWT token
	public String extractUsername(String token) {

		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
	}

}
