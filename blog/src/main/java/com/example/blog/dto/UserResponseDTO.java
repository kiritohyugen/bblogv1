package com.example.blog.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.blog.model.User;

public class UserResponseDTO {
	private Long id;
	private String username;
	private String email;
	private String name;
	private String bio;
	private String token;	
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.name = user.getName();
		this.bio = user.getBio();
		this.createdAt = user.getCreated_at();
		this.updatedAt = user.getUpdated_at();
	}
	
	public UserResponseDTO(String token) {
		this.token = token;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.username = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
