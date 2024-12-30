package com.example.blog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments", schema = "blog_app_schema")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Posts post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(updatable = false)
    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    // Getters and setters
}
