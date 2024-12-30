package com.example.blog.dto;

import java.sql.Timestamp;

import com.example.blog.model.Posts;

public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private UserPublicInfo user; // Include UserPublicInfo as part of the DTO

    // Constructor
    public PostDTO(Long id, String title, String content, String status, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt, UserPublicInfo user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.user = user;
    }

    public PostDTO() {
		// TODO Auto-generated constructor stub
	}

	public PostDTO(Posts post) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserPublicInfo getUser() {
        return user;
    }

    public void setUser(UserPublicInfo user) {
        this.user = user;
    }
}
