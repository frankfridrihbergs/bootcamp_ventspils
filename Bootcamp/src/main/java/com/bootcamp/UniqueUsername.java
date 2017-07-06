package com.bootcamp;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bootcamp.models.Users;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
	
	String message() default "username already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	

}
