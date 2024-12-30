package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.blog.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
	
		Optional<User> findByUsername(String username);
		
		Optional<User> findByEmail(String email);
		
		Optional<User> findByUsernameOrEmail(String username, String email);
		
	    // Custom query to fetch password by username (only if needed)
	    @Query("SELECT u.password FROM User u WHERE u.username = :username")
	    String findPasswordByUsername(@Param("username") String username);
	    
}
