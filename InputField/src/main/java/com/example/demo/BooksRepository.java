package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Books;


public interface BooksRepository extends CrudRepository<Books, Long> {

}
