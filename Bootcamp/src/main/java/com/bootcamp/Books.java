package com.bootcamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Books {
	
	@Id
	@Column(name = "title")
	private String title;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "book_condition")
	private String condition;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "book_count")
	private Integer count;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "pic_url")
	private String pic_url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		if(rating < 0){
			this.rating = 0;
		}else if (rating > 10){
			this.rating = 10;
		}
		else{
			this.rating = rating;
		}
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

}
