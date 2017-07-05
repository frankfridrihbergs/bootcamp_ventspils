package com.bootcamp.services;

import com.bootcamp.models.Users;

public interface UsersService {
	public Users findByUsername(String name);
	public void saveUser(Users user);
}