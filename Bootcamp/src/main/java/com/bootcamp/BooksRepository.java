package com.bootcamp;

import java.beans.Transient;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends CrudRepository<Books, Long>  {
	
	@Query("Select title from Books where book_condition = :book_condition")
	String getBook(@Param("book_condition") String book_condition);
	
	@Query("Select isbn from Books where title = :title")
	String getBookIsbn(@Param("title") String title);
	

}
