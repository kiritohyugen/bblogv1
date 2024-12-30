package com.example.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users",schema = "blog_app_schema")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(unique = true, length = 50)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 50)
	private String encryption_algorithm;

	@Column(length = 100)
	private String name;

	@Column(length = 100, columnDefinition = "TEXT")
	private String bio;
	
//    @ManyToOne
//    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = true)
//    private Image image;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp created_at;

	@UpdateTimestamp
	private Timestamp updated_at;

	private Timestamp deleted_at;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryption_algorithm() {
		return encryption_algorithm;
	}

	public void setEncryption_algorithm(String encryption_algorithm) {
		this.encryption_algorithm = encryption_algorithm;
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

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

}
