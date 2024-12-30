package com.example.blog.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
