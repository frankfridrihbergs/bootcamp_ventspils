package com.bootcamp.models;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bootcamp.UniqueUsername;

@Entity // This tells Hibernate to make a table out of this class
public class Users{
	
    private String name;
    private String surname;
    private String role;
    private String password;
    
    @Id
    @NotNull
    @UniqueUsername
    @Size(min = 4, max = 20)
    private String username;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

