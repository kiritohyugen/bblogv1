package com.example.blog.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "categories", schema = "blog_app_schema")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true, length = 100)
//    private String name;
//
//    @ManyToMany(mappedBy = "categories")
//    private Set<Posts> posts;
//
//    @Column(updatable = false)
//    private Timestamp createdAt;
//
//    private Timestamp updatedAt;

    // Getters and setters
}
