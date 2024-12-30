
package com.example.blog.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.dto.Request;
import com.example.blog.dto.UserLoginDTO;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.validation.UserValidation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class LoginService {

	private final UserValidation userValidation;
	private final TokenService tokenService;
	private final CacheManager cacheManager;
	private final UserRepository userRepository;
//    private final RedisTemplate<String, Object> redisTemplate; // Inject RedisTemplate

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public LoginService(UserValidation userValidation, TokenService tokenService, CacheManager cacheManager,
			UserRepository userRepository, PasswordEncoder passwordEncoder
			//,RedisTemplate<String, Object> redisTemplate
			) {
		this.userValidation = userValidation;
		this.tokenService = tokenService;
		this.cacheManager = cacheManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
//        this.redisTemplate = redisTemplate;

	}

	@Transactional
	public String loginUser(UserLoginDTO userLoginDTO) {

//	    // Step 1: Build cache key based on username and encoded password
//	    String cacheKey = userLoginDTO.getUsername() + ":" + passwordEncoder.encode(userLoginDTO.getPassword());
//
//	    // Step 2: Check if the token already exists in the cache
//	    Cache cache = cacheManager.getCache("userTokens");
//	    String token = null;
//
//	    if (cache != null) {
//	        // Check if token is available in the cache
//	        token = cache.get(cacheKey, String.class);
//	    }
		  // Step 1: Build a stable cache key using username and SHA-256 of the raw password
	    String passwordHash = DigestUtils.sha256Hex(userLoginDTO.getPassword());
	    String cacheKey = userLoginDTO.getUsername() + ":" + passwordHash;
	    // TODO enhance caching
	    // Step 2: Check if the token exists in the cache
	    Cache cache = cacheManager.getCache("userTokens");
	    String token = null;

	    if (cache != null) {
	        token = cache.get(cacheKey, String.class);
	    }


	    if (token == null) {
	        // Step 3: If no token in cache, validate the user and generate a new token
	        User dbUser = userValidation.validateUsernameAndPassword(userLoginDTO);
	        token = tokenService.generateToken(dbUser);

	        // Step 4: Store the token in cache with expiration time (e.g., 15 minutes)
	        if (cache != null) {
	            cache.put(cacheKey, token);

	            // Step 5: Set expiration time for the cache entry dynamically using RedisTemplate
	        }
	    }

	    return token;
	}


	public void logoutUser(@Valid Request<UserLoginDTO> request) {
		// Step 1: Validate if the token is valid using TokenService
		if (!tokenService.validateToken(request.getRequestInfo().getToken())) {
			throw new RuntimeException("Invalid or expired token. Cannot log out.");
		}

		// Step 2: Evict the token from the cache
		// Cache key will be in the format username:hashedPassword (the same as we used
		// for cacheable)
		 // Step 2: Extract the username from the token
	    String extractedUsername = tokenService.extractUsernameFromToken((request.getRequestInfo().getToken()));
	    if (!extractedUsername.equals(request.getData().getUsername())) {
	        throw new RuntimeException("Username in token does not match username in request.");
	    }
        User dbUser = userValidation.validateUsernameAndPassword(request.getData());
        if (dbUser == null) {
            throw new RuntimeException("Invalid username or password. Cannot log out.");
        }
        
     // Step 4: Hash the password using SHA-256 to reconstruct the cache key
        String passwordHash = DigestUtils.sha256Hex(request.getData().getPassword());
        String cacheKey = extractedUsername + ":" + passwordHash;

        // Step 5: Evict the token from Redis cache
        // Step 4: Check if the token exists in the cache before eviction
        Cache cache = cacheManager.getCache("userTokens");
        if (cache != null) {
            // Check if the token exists in the cache
            String existingToken = cache.get(cacheKey, String.class);

            if (existingToken != null) {
                // Evict the token from the cache
                cache.evict(cacheKey);
                System.out.println("User " + extractedUsername + " logged out and token evicted from cache.");
            } else {
                // If the token doesn't exist in the cache, throw an exception
                throw new RuntimeException("Token not found in cache. Cannot log out.");
            }
        } else {
            throw new RuntimeException("Cache not available. Cannot log out.");
        }

        // Optional: Log the successful logout action
	}

}
