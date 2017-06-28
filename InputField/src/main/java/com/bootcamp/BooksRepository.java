package com.bootcamp;

import org.springframework.data.repository.CrudRepository;

import com.bootcamp.Books;


public interface BooksRepository extends CrudRepository<Books, Long> {

}
