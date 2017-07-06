package com.bootcamp.repositories;

import java.beans.Transient;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.models.BookList;
import com.bootcamp.models.Books;


@Repository
public interface BookListRepository extends CrudRepository<BookList, Long> {
	
	//TODO
	// create more queries
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value="update book_list bl set bl.book_status = :bookStatus where bl.isbn = :isbn and bl.username = :username")
	void setBookstatusFor(@Param("bookStatus") String bookStatus, @Param("isbn") String isbn, @Param("username") String username);
	
	List<BookList> findByBookStatusIgnoreCase(String bookStatus);
	BookList findByIsbnAndUsername(String isbn, String username);
	
	@Query(value="select bl from book_list bl where bl.username = :username")
    List<BookList> findByUsername(@Param("username") String username);
    
    
    @Query("select bl from book_list bl where bl.return_date < :date")
    List<BookList> returnAllDebt(@Param("date")int date);
    
    @Query(nativeQuery = true, value="select * from book_list where username = :username and return_date <= :exp_time")
    List<BookList> findByUsernameAndExpiredBook (@Param("username")String username, @Param("exp_time") long expiredTime);
	
    @Transactional
	@Modifying
	@Query(nativeQuery = true, value="update book_list bl set bl.return_date = :return_date where bl.isbn = :isbn and bl.username = :username")
	void setReturnDateFor(@Param("return_date") long return_date, @Param("isbn") String isbn, @Param("username") String username);
}