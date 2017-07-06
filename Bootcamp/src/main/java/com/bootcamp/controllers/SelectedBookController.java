package com.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.models.BookList;
import com.bootcamp.models.Books;
import com.bootcamp.models.Users;
import com.bootcamp.repositories.BookListRepository;
import com.bootcamp.repositories.BooksRepository;
import com.bootcamp.repositories.UserRepository;

@Controller
public class SelectedBookController {

	@Autowired
	BooksRepository bookrepository;
	
	@Autowired
	BookListRepository bookListRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value={"/selectedBook"}, method = RequestMethod.GET)
	public ModelAndView selectedBook(@RequestParam(value="title")String title){
		ModelAndView modelAndView = new ModelAndView();
		Books book = bookrepository.findFirstByTitleIgnoreCase(title);
		modelAndView.addObject("title", book.getTitle());
		modelAndView.addObject("pic_url", book.getPic_url());
		modelAndView.setViewName("selectedBook"); //demo or JAVA
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println("logged user: " + name);
		
		return modelAndView;
	}
	
	@RequestMapping(value={"/takebook"}, method = RequestMethod.GET)
	public ModelAndView addBook(@RequestParam(value="title")String title){
		
		
		ModelAndView modelAndView = new ModelAndView();
		BookList bookList = new BookList();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		System.out.println("title from title: " + title);
		
		//Users user = userRepository.findByUsername(username);
		Books book = bookrepository.findFirstByTitleIgnoreCase(title);
		
		int availableBookCount =  bookrepository.getCountofTheSameBooksByTitle(title);
		int reservedBookCount = bookListRepository.getCountofReservedBooksByTitle(title);
		
		System.out.println("Reserved book count: " + reservedBookCount);
		System.out.println("Available book count: " + availableBookCount);
		
		if(reservedBookCount < availableBookCount){
			
			modelAndView.addObject("availability", "order");
			bookList.setBookStatus("ordered");
			
		}
		else{
			modelAndView.addObject("availability", "inque");
			bookList.setBookStatus("que");
		}
		
		bookList.setBookStatus("ordered");
		bookList.setIsbn(book.getIsbn());
		bookList.setTitle(title);
		bookList.setUsername(username);
		bookList.setAuthor(book.getAuthor());
		
		bookListRepository.save(bookList);
		
		modelAndView.setViewName("redirect:/availableBooks"); //demo or JAVA
		return modelAndView;
	}
	
	
	
	/*@RequestMapping(value={"/selectedBook"}, method = RequestMethod.POST)
	public ModelAndView SendRequest(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("book",book);
		//model.addAttribute("author", author);
		//model.addAttribute("year", year);
		//model.addAttribute("isbn", isbn);
		modelAndView.setViewName("test2");
		return modelAndView;
	}
	*/
}
