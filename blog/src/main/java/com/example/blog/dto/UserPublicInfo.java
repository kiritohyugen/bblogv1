package com.example.blog.dto;

public class UserPublicInfo {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String bio;

    // Constructor, Getters, and Setters
    public UserPublicInfo(Long id, String name, String email, String username, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.bio = bio;
    }
    
    // Constructor, Getters, and Setters
    public UserPublicInfo(Long id,String email, String username, String bio) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.bio = bio;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
