package com.bootcamp.services;

import java.util.Optional;

import com.bootcamp.models.Users;

public interface UsersService {
	public Optional <Users> findByUsername(String name);
	public void saveUser(Users user);
}