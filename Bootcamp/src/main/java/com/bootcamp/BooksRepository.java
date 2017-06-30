package com.bootcamp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("bookRepository")
public interface BooksRepository extends CrudRepository<Books, Long> {
	
	Books deleteByIsbn(String isbn);

}
