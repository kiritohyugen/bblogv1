package com.example.blog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "tags", schema = "blog_app_schema")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @Column(nullable = false, unique = true, length = 100)
//    private String name;
//
//    @ManyToMany(mappedBy = "tags")
//    private Set<Posts> posts;
//
//    @Column(updatable = false)
//    private Timestamp createdAt;
//
//    private Timestamp updatedAt;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Set<Posts> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(Set<Posts> posts) {
//		this.posts = posts;
//	}
//
//	public Timestamp getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Timestamp createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Timestamp getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Timestamp updatedAt) {
//		this.updatedAt = updatedAt;
//	}

    // Getters and setters
}
