package com.bootcamp;


public interface UsersService {
	public Users findByUsername(String name);
	public void saveUser(Users user);
}