package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.Request;
import com.example.blog.dto.Response;
import com.example.blog.dto.ResponseInfo;
import com.example.blog.dto.UserLoginDTO;
import com.example.blog.dto.UserResponseDTO;
import com.example.blog.dto.UserSignupDTO;
import com.example.blog.model.User;
import com.example.blog.service.LoginService;
import com.example.blog.service.SignupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final SignupService signupService;
    private final LoginService loginService;
    
    @Autowired
    public UserController(SignupService signupService,LoginService loginService) {
        this.signupService = signupService;
        this.loginService = loginService;
    }

    
    @PostMapping("/signup")
    public ResponseEntity<Response<UserResponseDTO>> signupUser(@Valid @RequestBody Request<UserSignupDTO> request) {

        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            // Extracting the UserSignupDTO from the request
//            String token = signupService.signupUser(request.getData());  // Service handles user creation and token generation
            User user = signupService.signupUser(request.getData());  // Service handles user creation and token generation

            // Setting success message and token in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("User registered successfully.");

            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            // Return the wrapped response with CREATED status
//            Response<String> response = new Response<>(responseInfo, token);
            Response<UserResponseDTO> response = new Response<>(responseInfo, userResponseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // If there’s an error, return it in the response with a BAD_REQUEST status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during signup: " + e.getMessage());

            Response<UserResponseDTO> response = new Response<>(responseInfo, null); // Return error message without token
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<Response<UserResponseDTO>> loginUser(@Valid @RequestBody Request<UserLoginDTO> request) {

        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            // Extracting the UserSignupDTO from the request
            String token = loginService.loginUser(request.getData());  // Service handles user creation and token generation

            // Setting success message and token in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("User login successfully.");

            UserResponseDTO userResponseDTO = new UserResponseDTO(token);
            Response<UserResponseDTO> response = new Response<>(responseInfo, userResponseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // If there’s an error, return it in the response with a BAD_REQUEST status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during login: " + e.getMessage());

            Response<UserResponseDTO> response = new Response<>(responseInfo, null); // Return error message without token
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<UserResponseDTO>> logoutUser(@Valid @RequestBody Request<UserLoginDTO> request) {

        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            // Extracting the token from request and passing it to logout method
            String token = request.getRequestInfo().getToken();
            
            // Logging out the user by evicting the cached token
            loginService.logoutUser(request);  // Service handles cache eviction and other logout-related tasks

            // Setting success message in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("User logged out successfully.");

            Response<UserResponseDTO> response = new Response<>(responseInfo, null);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

        } catch (Exception e) {
            // If there’s an error, return it in the response with a BAD_REQUEST status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during logout: " + e.getMessage());

            Response<UserResponseDTO> response = new Response<>(responseInfo, null); // Return error message without token
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
