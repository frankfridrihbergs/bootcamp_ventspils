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
	public ModelAndView takeBook(@RequestParam(value = "isbn") String isbn){
		System.out.println("giving book with isbn: " + isbn);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    Users user = userService.findByUsername(name);
	    
	    BookList bookList = new BookList();
	    bookListRepository.setBookstatusFor("take", isbn, "dplasis");
	    
	    System.out.println("Book has been given");
	    
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook");
	    mav.addObject("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
	    mav.addObject("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
	    mav.setViewName("redirect:/givetakebook");
	    if(user == null){
	    	System.out.println("Error: user is null");
	    	return mav;
	    }
        //mav.addObject("takenBooks", user.getTakenBooks());
	    
	   /* if(user.isBookTaken(isbn)){
	    	return mav; //TODO show messege if user doesnt have book with that isbn number
	    }
	    for (Books book : bookRepository.findAll()) { 
			if(book.getIsbn() == isbn ){ 	//find book by isbn
				book.setBookAsAvailable();
				user.deleteTakenBook(isbn);
				bookRepository.save(book);
				userService.saveUser(user);
				return "givetakebook";
			}
		}*/
	    
		return mav;	//TODO show message if there isn't book with given isbn number
	}
	
	@RequestMapping(value="/takebook", method = RequestMethod.POST)
	public ModelAndView giveBookByISBN(@RequestParam(value = "isbn") String isbn){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 		//get logged in username
	    Users user = userService.findByUsername(name);
	    bookListRepository.setBookstatusFor("ordered", isbn, "dplasis");
	    
	    System.out.println("Book has been taken");
	    
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook");
	    mav.addObject("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
	    mav.addObject("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
	    mav.setViewName("redirect:/givetakebook");
	    if(user == null){ //check if user exists
	    	System.out.println("Error: user is null");
	    	return mav;
	    }
		/*for (Books book : bookRepository.findAll()) {
			if(book.getIsbn() == isbn ){
				if(book.getAvalability()=="true"){	 	//find book by isbn
					return "givetakebook";  //TODO show message if book with given isbn number is taken
				}
				book.setBookAsTaken();
				user.addTakenBook(book);
				bookRepository.save(book);
				userService.saveUser(user);
				return "givetakebook";
			}
		}*/
		return mav; //TODO show message if there isn't book with given isbn number
	}
}
