package com.bootcamp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.models.Users;
import com.bootcamp.repositories.UserRepository;


@Service("userService")
public class UserServiceImpl implements UsersService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional <Users> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(Users user) {
		userRepository.save(user);
	}

}