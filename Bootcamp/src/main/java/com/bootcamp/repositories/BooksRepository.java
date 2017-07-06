package com.bootcamp.repositories;

import java.beans.Transient;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bootcamp.models.Books;


@Repository
public interface BooksRepository extends CrudRepository<Books, Long>  {
	
	
	@Query(nativeQuery = true, value="select count(*) from books where title = :title")
	int getCountofTheSameBooksByTitle(@Param("title")String title);
	
	@Query("select b from Books b where b.title like %:title% and b.author like %:author%" //Will return all books that match criteria (title,author,isbn,rating)
			+ " and b.rating >= :rating and b.isbn like %:isbn%")
	List<Books> getBooksByInfo(@Param("title") String title,@Param("author") String author,
			@Param("rating") Float rating, @Param("isbn") String isbn);
	
	List<Books> findByTitleIgnoreCase(String title);
	
	Books findFirstByTitleIgnoreCase(String title);
	
	List<Books> findByTitleOrAuthorOrIsbnIgnoreCaseOrRating(String title, String author, String isbn, float rating);
	
}
