package com.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UsersService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(Users user) {
		userRepository.save(user);
	}

}