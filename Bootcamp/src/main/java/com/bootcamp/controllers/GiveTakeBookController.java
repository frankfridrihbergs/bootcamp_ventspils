package com.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.models.BookList;
import com.bootcamp.models.Users;
import com.bootcamp.repositories.BookListRepository;
import com.bootcamp.repositories.BooksRepository;
import com.bootcamp.services.UsersService;

@Controller
public class GiveTakeBookController {
	
	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private BookListRepository bookListRepository;
	
	@RequestMapping(value={"/givetakebook"}, method = RequestMethod.GET)
	public String givetakeBook(Model model){
		model.addAttribute("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
		model.addAttribute("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
		
		return "givetakebook";
	}
	
	@RequestMapping(value={"/givebook"}, method = RequestMethod.POST)
	public ModelAndView takeBook(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "username") String username){
		System.out.println("giving book with isbn: " + isbn);
		System.out.println("giving book for user: " + username);
		
		bookListRepository.setBookstatusFor("owned", isbn, username); //change book status to "owned"
	    
	    System.out.println("Book has been given");
	    
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook");
	    mav.addObject("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
	    mav.addObject("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
	    mav.setViewName("redirect:/givetakebook");
	    
	    
		return mav;	//TODO show message if there isn't book with given isbn number
	}
	
	@RequestMapping(value="/takebook", method = RequestMethod.POST)
	public ModelAndView giveBookByISBN(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "username") String username){
	    System.out.println("Book has been taken");
		//bookListRepository.setBookstatusFor("ordered", isbn, username); //change book status to "ordered"
		bookListRepository.delete(bookListRepository.findByIsbnAndUsername(isbn, username)); //delete book from bookList
		
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook");
	    mav.addObject("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
	    mav.addObject("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
	    mav.setViewName("redirect:/givetakebook");
	    
		return mav; //TODO show message if there isn't book with given isbn number
	}
}
