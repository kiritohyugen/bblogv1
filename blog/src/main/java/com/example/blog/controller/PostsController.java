package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.PostDTO;
import com.example.blog.dto.Request;
import com.example.blog.dto.Response;
import com.example.blog.dto.ResponseInfo;
import com.example.blog.service.PostsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    // Create a new post
    @PostMapping
    public ResponseEntity<Response<PostDTO>> createPost(@Valid @RequestBody Request<PostDTO> request) {
        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            // Extracting the PostDTO from the request
            PostDTO createdPost = postsService.createPost(request);  // Service handles post creation

            // Setting success message and post in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("Post created successfully.");

            // Return the wrapped response with CREATED status
            Response<PostDTO> response = new Response<>(responseInfo, createdPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // If there’s an error, return it in the response with a BAD_REQUEST status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during post creation: " + e.getMessage());

            Response<PostDTO> response = new Response<>(responseInfo, null);  // Return error message without post
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Update an existing post
    @PutMapping
    public ResponseEntity<Response<PostDTO>> updatePost(@Valid @RequestBody Request<PostDTO> request) {
        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            // Extracting the PostDTO from the request
            PostDTO updatedPost = postsService.updatePost(request);  // Service handles post update

            // Setting success message and updated post in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("Post updated successfully.");

            // Return the wrapped response with OK status
            Response<PostDTO> response = new Response<>(responseInfo, updatedPost);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // If there’s an error, return it in the response with a NOT_FOUND status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during post update: " + e.getMessage());

            Response<PostDTO> response = new Response<>(responseInfo, null);  // Return error message without updated post
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete a post
    @PostMapping("/delete")
    public ResponseEntity<Response<Void>> deletePost(@Valid @RequestBody Request<PostDTO> request) {
        // Creating ResponseInfo with default success = false, message = "Processing"
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            postsService.deletePost(request);  // Service handles post deletion

            // Setting success message in response
            responseInfo.setSuccess(true);
            responseInfo.setMessage("Post deleted successfully.");

            Response<Void> response = new Response<>(responseInfo, null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // If there’s an error, return it in the response with a BAD_REQUEST status
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during post deletion: " + e.getMessage());

            Response<Void> response = new Response<>(responseInfo, null);  // Return error message without post
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    // Get all posts
    @PostMapping("/getAll")
    public ResponseEntity<Response<List<PostDTO>>> getAllPosts(@Valid @RequestBody Request<Void> request) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setSuccess(false);
        responseInfo.setMessage("Processing");

        try {
            List<PostDTO> posts = postsService.getAllPosts(request);  // Service fetches all posts

            responseInfo.setSuccess(true);
            responseInfo.setMessage("Posts fetched successfully.");

            Response<List<PostDTO>> response = new Response<>(responseInfo, posts);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            responseInfo.setSuccess(false);
            responseInfo.setMessage("Error during fetching posts: " + e.getMessage());

            Response<List<PostDTO>> response = new Response<>(responseInfo, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}


