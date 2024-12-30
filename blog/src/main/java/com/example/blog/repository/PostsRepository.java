package com.example.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.dto.PostDTO;
import com.example.blog.model.Posts;

public interface PostsRepository  extends JpaRepository<Posts, Long> {

	// Find all posts by a specific user
	Optional<Posts> findByUserId(Long userId);

    // Find a post by its ID
    Optional<Posts> findById(Long id);

    // Find posts with a specific status (e.g., "draft", "published")
    Optional<Posts> findByStatus(String status);

    // Custom query example: Find posts by a part of the title or content
    Optional<Posts> findByTitleContainingOrContentContaining(String title, String content);
    

    // Query to fetch posts and map user details to UserPublicInfo
    @Query("SELECT new com.example.blog.dto.PostDTO(p.id, p.title, p.content, p.status, p.createdAt, p.updatedAt, p.deletedAt, " +
            "new com.example.blog.dto.UserPublicInfo(u.id, u.name, u.email, u.username, u.bio)) " +
            "FROM Posts p JOIN User u ON p.userId = u.id")
    List<PostDTO> findAllPostsWithUserInfo();
    
}
