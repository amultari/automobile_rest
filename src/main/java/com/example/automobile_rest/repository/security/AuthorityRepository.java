package com.example.automobile_rest.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.automobile_rest.model.security.Authority;
import com.example.automobile_rest.model.security.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}