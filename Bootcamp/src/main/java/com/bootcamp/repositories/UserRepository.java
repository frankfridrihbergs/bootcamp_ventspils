package com.bootcamp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Optional <Users> findByUsername(String username);
}