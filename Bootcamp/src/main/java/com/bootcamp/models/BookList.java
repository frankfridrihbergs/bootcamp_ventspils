package com.bootcamp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="book_list")
public class BookList {
	
	@Id
	@Column(name = "username")
	private String usrname;
	
	@Column(name = "book_status")
	private String bookStatus;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "isbn")
	private String isbn;
	
	
}
