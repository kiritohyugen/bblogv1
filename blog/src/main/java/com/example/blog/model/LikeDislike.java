package com.example.blog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "likes_dislikes", schema = "blog_app_schema", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "targetType", "targetId"}))
public class LikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 20)
    private String targetType;

    @Column(nullable = false)
    private Long targetId;
    

	@Column()
    private Boolean isLiked;

    @Column(updatable = false)
    private Timestamp createdAt;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
