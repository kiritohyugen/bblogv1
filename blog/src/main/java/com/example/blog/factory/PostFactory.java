package com.example.blog.factory;

import com.example.blog.dto.PostDTO;
import com.example.blog.model.Posts;
import com.example.blog.model.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class PostFactory {

    public Posts createPost(PostDTO postDTO, User user) {
        Posts post = new Posts();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setStatus(postDTO.getStatus());
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        post.setUserId(user.getId()); // Setting the actual user entity
        return post;
    }

    public Posts updatePost(PostDTO postDTO, Posts existingPost) {
        existingPost.setTitle(postDTO.getTitle());
        existingPost.setContent(postDTO.getContent());
        existingPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return existingPost;
    }
}
