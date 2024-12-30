package com.example.blog.model;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts", schema = "blog_app_schema")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Only store userId

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 50)
    private String status = "draft";

//    @ManyToMany
//    @JoinTable(
//        name = "post_tags",
//        joinColumns = @JoinColumn(name = "post_id"),
//        inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private Set<Tag> tags;
//
//    @ManyToMany
//    @JoinTable(
//        name = "post_categories",
//        joinColumns = @JoinColumn(name = "post_id"),
//        inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private Set<Category> categories;

    @Column(updatable = false)
    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

//	public Set<Tag> getTags() {
//		return tags;
//	}
//
//	public void setTags(Set<Tag> tags) {
//		this.tags = tags;
//	}
//
//	public Set<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(Set<Category> categories) {
//		this.categories = categories;
//	}

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

    // Getters and setters
}
