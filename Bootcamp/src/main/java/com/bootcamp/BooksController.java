package com.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.Books;
import com.bootcamp.BooksRepository;

@Controller(value="BooksController")    // This means that this class is a Controller
@RequestMapping(path="/book") // This means URL's start with /book (after Application path)
public class BooksController  {
	
	@Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
	private BooksRepository bookRepository;
	
	/*
	 public @ResponseBody String addNewBook (@RequestParam String title
			, @RequestParam String isbn, @RequestParam String author,
			@RequestParam String condition, @RequestParam int year, @RequestParam int count, @RequestParam float rating, @RequestParam String pic_url) {
		
	  
	 */
	
	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewBook (@RequestParam String title
			, @RequestParam String isbn, @RequestParam String author,
			@RequestParam float rating) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Books book = new Books();
		/*book.setCondition(condition);
		book.setCount(count);
		book.setIsbn(isbn);
		book.setRating(rating);
		book.setYear(year);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPic_url("asdfg");*/
		
		book.setCondition("new");
		book.setCount(1);
		book.setIsbn("1234567890123");
		book.setRating(rating);
		book.setYear(2012);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPic_url("asdfg");
		
		bookRepository.save(book);

		return "Saved";
	}
	
	@GetMapping(path="/delete") 
	public @ResponseBody String deleteBook(@RequestParam String isbn) {
		
		//bookRepository.deleteByIsbn(isbn);
		
		return "deleted";
		
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Books> getAllUsers() {
		// This returns a JSON or XML with the users
		return bookRepository.findAll();
	}

}
