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
public interface BookListRepository extends CrudRepository<Books, Long>  {
	
	//TODO
	// create more queries
	
}
