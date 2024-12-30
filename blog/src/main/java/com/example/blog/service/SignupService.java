
package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.dto.UserSignupDTO;
import com.example.blog.factory.UserFactory;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.validation.UserValidation;

import jakarta.transaction.Transactional;

@Service
public class SignupService {

	private final UserRepository userRepository;
	private final UserValidation userValidation;
	private final UserFactory userFactory;
	private final TokenService tokenService;

	@Autowired
	public SignupService(UserRepository userRepository, UserValidation userValidation, UserFactory userFactory,
			TokenService tokenService) {
		this.userRepository = userRepository;
		this.userValidation = userValidation;
		this.userFactory = userFactory;
		this.tokenService = tokenService;
	}

//	@Transactional
//	public String signupUser(UserSignupDTO userSignupDTO) {
//
//		userValidation.validateUsername(userSignupDTO.getUsername());
//		userValidation.validateEmail(userSignupDTO.getEmail());
//
//		// Create a new user using the factory
//		User newUser = userFactory.createUser(userSignupDTO);
//
//		// Save the user to the database
//		userRepository.save(newUser);
//
//		// Generate and return the JWT token
//		return tokenService.generateToken(newUser); // Use TokenService for token generation
//	}
	
	@Transactional
	public User signupUser(UserSignupDTO userSignupDTO) {

		userValidation.validateUsername(userSignupDTO.getUsername());
		userValidation.validateEmail(userSignupDTO.getEmail());

		// Create a new user using the factory
		User newUser = userFactory.createUser(userSignupDTO);

		// Save the user to the database
		userRepository.save(newUser);

		// Generate and return the JWT token
		return newUser;
	}
}
