package com.bootcamp;


public interface UserService {
	public User findByUsername(String name);
	public void saveUser(User user);
}