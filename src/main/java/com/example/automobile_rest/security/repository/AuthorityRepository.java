package com.example.automobile_rest.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.automobile_rest.model.Authority;
import com.example.automobile_rest.model.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}