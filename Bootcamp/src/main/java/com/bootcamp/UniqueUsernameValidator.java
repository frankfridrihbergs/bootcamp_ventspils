package com.bootcamp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bootcamp.repositories.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private UserRepository userReposritory;
	
	public UniqueUsernameValidator(UserRepository userReposritory) {
		// TODO Auto-generated method stub
		this.userReposritory = userReposritory;
		
	}
	
	@Override
	public void initialize(UniqueUsername constraint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		return username != null && !userReposritory.findByUsername(username).isPresent();
	}

}
