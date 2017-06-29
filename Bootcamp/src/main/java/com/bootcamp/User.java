package com.bootcamp;


import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private int libraryUserNumber;
    private List<Books> books;
    
	public int getLibraryUserNumber() {
		return libraryUserNumber;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}

	public void setLibraryUserNumber(int libraryUserNumber) {
		this.libraryUserNumber = libraryUserNumber;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	//public string getCurrentTakenBookList(){
	//	return books;
	//}

	//public string loadHistory() 
	
	
	
	public void setId(Integer id) {
		this.id = id;
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
	
	//public String login(String loginName, String loginPass) {
		
		
		
	//}
    
    
}

