package com.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.models.Users;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, Long> {
	 Users findByUsername(String username);
}