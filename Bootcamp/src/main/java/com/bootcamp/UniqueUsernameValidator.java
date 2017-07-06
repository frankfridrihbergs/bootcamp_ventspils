package com.bootcamp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.repositories.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private UserRepository userReposritory;
	
	public UniqueUsernameValidator(){}
	
	public void initialize(UniqueUsername constraint) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		if(this.userReposritory == null){
			return true;
		}
		
		return username != null && !this.userReposritory.findByUsername(username).isPresent();
	}


}
