package com.example.automobile_rest.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.automobile_rest.model.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}