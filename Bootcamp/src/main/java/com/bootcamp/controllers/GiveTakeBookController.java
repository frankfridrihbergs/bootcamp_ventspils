package com.bootcamp.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.repositories.BookListRepository;
import com.bootcamp.repositories.BooksRepository;
import com.bootcamp.services.UsersService;

@Controller
public class GiveTakeBookController {
	
	@Autowired
	private BookListRepository bookListRepository;
	
	@RequestMapping(value={"/givetakebook"}, method = RequestMethod.GET)
	public String givetakeBook(Model model){
		model.addAttribute("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
		model.addAttribute("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	    //get logged in user username
		//String name = auth.getName(); 
	    //get logged in user data
		//Users user = userService.findByUsername(name); 
		//System.out.println(user.getName());
		
		return "givetakebook";
	}
	
	@RequestMapping(value={"/givebook"}, method = RequestMethod.POST)
	public ModelAndView takeBook(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "date", defaultValue="none") String date){
		ModelAndView mav = new ModelAndView("redirect:/givetakebook");
		String[] isbnAndUsername = isbn.split(","); //split isbn and username where comma
		isbn = isbnAndUsername[0]; //get isbn
		String username = isbnAndUsername[1]; //get username
	    
	    if(date.equals("none")){
	    	mav.setViewName("redirect:/givetakebook?noTimeSet");
			return  mav;
	    }
	    
		System.out.println("giving book with isbn: " + isbn);
		System.out.println("giving book for user: " + username);
		Date newDate = new Date();
		Date returnDate = new Date(date); //Change date type to Date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(newDate);
		calendar.add(Calendar.MONTH, 1); 
		Date dateAfterMonth = calendar.getTime(); //get date after 1 month
		calendar.setTime(newDate);
		calendar.add(Calendar.DATE, 5); 
		Date dateAfterFiveDays = calendar.getTime(); //get date after 5 days
		System.out.println(dateAfterFiveDays);
		if(dateAfterFiveDays.after(returnDate)){
			mav.setViewName("redirect:/givetakebook?tooLessTime");
			return  mav;
		}
		else if(dateAfterMonth.before(returnDate)){
			mav.setViewName("redirect:/givetakebook?tooMuchTime");
			return  mav;
		}
		
		
		bookListRepository.setBookstatusFor("owned", isbn, username); //change book status to "owned"
		bookListRepository.setReturnDateFor(returnDate.getTime(), isbn, username); //set book return date
	    System.out.println("Book has been given");

	    
		return mav;	
	}
	
	@RequestMapping(value="/takebook", method = RequestMethod.POST)
	public ModelAndView giveBookByISBN(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "username") String username){
	    System.out.println("Book has been taken");
		bookListRepository.delete(bookListRepository.findByIsbnAndUsername(isbn, username)); //delete book from bookList
		
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook");
	    mav.addObject("books", bookListRepository.findByBookStatusIgnoreCase("ordered"));
	    mav.addObject("books1", bookListRepository.findByBookStatusIgnoreCase("take"));
	    mav.setViewName("redirect:/givetakebook");
	    
		return mav; 
	}
}
