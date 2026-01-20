package com.example.gimpo.gpUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GpUserRepository extends JpaRepository<GpUser, Long> {
	Optional<GpUser> findByUsername(String username);
}
