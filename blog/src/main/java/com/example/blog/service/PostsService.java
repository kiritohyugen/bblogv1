//package com.example.blog.service;
//
//import com.example.blog.dto.PostDTO;
//import com.example.blog.dto.Request;
//import com.example.blog.dto.UserPublicInfo;
//import com.example.blog.model.Posts;
//import com.example.blog.model.User;
//import com.example.blog.repository.PostsRepository;
//import com.example.blog.repository.UserRepository;
//import com.example.blog.validation.UserValidation;
//
//import jakarta.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PostsService {
//
//    private final PostsRepository postsRepository;
//	private final UserValidation userValidation;
//	private final TokenService tokenService;
//	private final CacheManager cacheManager;
//	private final UserRepository userRepository;
//
//	@Autowired
//	public PostsService(UserValidation userValidation, TokenService tokenService, CacheManager cacheManager,PostsRepository postsRepository
//			,UserRepository userRepository) {
//		this.userValidation = userValidation;
//		this.tokenService = tokenService;
//		this.cacheManager = cacheManager;
//		this.userRepository = userRepository;
//		this.postsRepository = postsRepository;
//
//	}
//
//    @Transactional
//	public PostDTO createPost(@Valid Request<PostDTO> request) {
//		if (!tokenService.validateToken(request.getRequestInfo().getToken())) {
//			throw new RuntimeException("Invalid or expired token. Cannot log out.");
//		}
//	    String extractedUsername = tokenService.extractUsernameFromToken((request.getRequestInfo().getToken()));
//	    Optional<User> user= userRepository.findByUsername(extractedUsername);
//	    PostDTO postDTO = request.getData();
//        // Convert PostDTO to Posts entity
//        Posts post = new Posts();
//        post.setTitle(postDTO.getTitle());
//        post.setContent(postDTO.getContent());
//        post.setStatus(postDTO.getStatus());
//        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//        post.setUserId(user.get().getId());  // Setting the actual user entity
//        
//        Posts savedPost = postsRepository.save(post);
//
//        
//		// TODO Auto-generated method stub
//        // Convert saved post to PostDTO and return
//        return new PostDTO(savedPost.getId(),
//                          savedPost.getTitle(),
//                          savedPost.getContent(),
//                          savedPost.getStatus(),
//                          savedPost.getCreatedAt(),
//                          savedPost.getUpdatedAt(),
//                          savedPost.getDeletedAt(),
//                          new UserPublicInfo(
//                        		  user.get().getEmail(),
//                        		  user.get().getUsername(),
//                        		  user.get().getBio())
//                          ); 
//        
//	}
//
//    @Transactional
//    public PostDTO updatePost(@Valid Request<PostDTO> request) {
//        // Validate the token (ensure it's valid and not expired)
//        if (!tokenService.validateToken(request.getRequestInfo().getToken())) {
//            throw new RuntimeException("Invalid or expired token. Cannot update post.");
//        }
//        
//        // Extract the username from the token
//        String extractedUsername = tokenService.extractUsernameFromToken(request.getRequestInfo().getToken());
//        Optional<User> user = userRepository.findByUsername(extractedUsername);
//        // Extract the post ID from the request
//        Long postId = request.getData().getId();
//        
//        // Fetch the post from the database to ensure it exists and belongs to the user
//        Posts post = postsRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
//        
//        // Fetch the user associated with the post and check if the user matches the one in the token
//        if (!post.getUserId().equals(user.get().getId())) {
//            throw new RuntimeException("User is not authorized to update this post.");
//        }
//        
//        // If the user is authorized, update the post's content
//        post.setTitle(request.getData().getTitle());
//        post.setContent(request.getData().getContent());
//        
//        // Save the updated post to the database
//        post = postsRepository.save(post);
//
//        // Convert the updated post entity to a PostDTO
//        PostDTO updatedPostDTO = new PostDTO();
//        updatedPostDTO.setId(post.getId());
//        updatedPostDTO.setTitle(post.getTitle());
//        updatedPostDTO.setContent(post.getContent());
//        updatedPostDTO.setCreatedAt(post.getCreatedAt());
//        updatedPostDTO.setUpdatedAt(post.getUpdatedAt());
//
//        // Return the updated post data
//        return updatedPostDTO;
//    }
//
//
//    @Transactional
//	public void deletePost(@Valid Request<PostDTO> request) {
//		// TODO Auto-generated method stub
//        if (!tokenService.validateToken(request.getRequestInfo().getToken())) {
//            throw new RuntimeException("Invalid or expired token. Cannot update post.");
//        }
//        
//        // Extract the username from the token
//        String extractedUsername = tokenService.extractUsernameFromToken(request.getRequestInfo().getToken());
//        Optional<User> user = userRepository.findByUsername(extractedUsername);
//        // Extract the post ID from the request
//        Long postId = request.getData().getId();
//        
//        // Fetch the post from the database to ensure it exists and belongs to the user
//        Posts post = postsRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
//        
//        // Fetch the user associated with the post and check if the user matches the one in the token
//        if (!post.getUserId().equals(user.get().getId())) {
//            throw new RuntimeException("User is not authorized to update this post.");
//        }
//        
//        Optional<Posts> postOpt = postsRepository.findById(postId);
//
//        if (postOpt.isPresent()) {
//            postsRepository.deleteById(postId);
//        } else {
//            throw new RuntimeException("Post not found with id: " + postId);
//        }
//	}
//}



package com.example.blog.service;

import com.example.blog.dto.PostDTO;
import com.example.blog.dto.Request;
import com.example.blog.dto.UserPublicInfo;
import com.example.blog.factory.PostFactory;
import com.example.blog.model.Posts;
import com.example.blog.model.User;
import com.example.blog.repository.PostsRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.validation.TokenValidationService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final TokenValidationService tokenValidationService;
    private final PostFactory postFactory;
	private final UserRepository userRepository;


    @Autowired
    public PostsService(PostsRepository postsRepository, TokenValidationService tokenValidationService, PostFactory postFactory, UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.tokenValidationService = tokenValidationService;
        this.postFactory = postFactory;
		this.userRepository = userRepository;

    }

    @Transactional
    public PostDTO createPost(Request<PostDTO> request) {
        // Validate token and extract user
        User user = tokenValidationService.validateAndGetUser(request.getRequestInfo().getToken());

        // Create post using factory
        Posts post = postFactory.createPost(request.getData(), user);

        // Save post to repository
        Posts savedPost = postsRepository.save(post);

        // Convert saved post to PostDTO and return
        return mapToPostDTO(savedPost, user);
    }

    @Transactional
    public PostDTO updatePost(Request<PostDTO> request) {
        // Validate token and extract user
        User user = tokenValidationService.validateAndGetUser(request.getRequestInfo().getToken());

        Long postId = request.getData().getId();
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        // Ensure the post belongs to the logged-in user
        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("User is not authorized to update this post.");
        }

        // Update the post using the factory
        post = postFactory.updatePost(request.getData(), post);

        // Save the updated post to the repository
        Posts updatedPost = postsRepository.save(post);

        // Return the updated post DTO
        return mapToPostDTO(updatedPost, user);
    }

    @Transactional
    public void deletePost(Request<PostDTO> request) {
        // Validate token and extract user
        User user = tokenValidationService.validateAndGetUser(request.getRequestInfo().getToken());

        Long postId = request.getData().getId();
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));

        // Ensure the post belongs to the logged-in user
        if (!post.getUserId().equals(user.getId())) {
            throw new RuntimeException("User is not authorized to delete this post.");
        }

        // Delete the post
        postsRepository.deleteById(postId);
    }

    private PostDTO mapToPostDTO(Posts post, User user) {
        return new PostDTO(post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getStatus(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getDeletedAt(),
                new UserPublicInfo(
                		user.getId(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getBio()
                )
        );
    }

    @Transactional
    public List<PostDTO> getAllPosts(Request<Void> request) {
        // Step 1: Validate token and extract user
        User user = tokenValidationService.validateAndGetUser(request.getRequestInfo().getToken());

        // Step 2: Fetch all posts
        List<Posts> posts = postsRepository.findAll();

        // Step 3: Iterate over each post and convert it to PostDTO
        List<PostDTO> postDTOs = posts.stream().map(post -> {
            // Get the user corresponding to the post's userId
            Long userId = post.getUserId();
            User postUser = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            // Return a PostDTO for each post with corresponding user data
            return mapToPostDTO(post, postUser);
        }).collect(Collectors.toList());

        // Step 4: Return the list of PostDTOs
        return postDTOs;
    }
}

