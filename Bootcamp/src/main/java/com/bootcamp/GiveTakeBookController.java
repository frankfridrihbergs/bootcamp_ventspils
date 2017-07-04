package com.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // sets class as controller 
public class GiveTakeBookController {
	
	@Autowired // gets book repository 
	private BooksRepository bookRepository; 
	
	@Autowired // gets user repository 
	private UsersService userService;
	
	// sets /givetakebook path. Called when using get method
	@RequestMapping(value={"/givetakebook"}, method = RequestMethod.GET) 
	// method which will be called by path /givetakebook with method GET
	public String givetakeBook(Model model){
		//add list of books to model
		model.addAttribute("books", bookRepository.findAll()); 
		/*//get logged in user 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	    //get logged in user username
	    String name = auth.getName(); 
	    //get logged in user data
	    Users user = userService.findByUsername(name); 
		model.addAttribute(user.getTeakenBooks());*/
		
		return "givetakebook";
	}
	//  sets /givebook path. Called when using POST method
	@RequestMapping(value={"/givebook"}, method = RequestMethod.POST)
	// binds values isbn, taken from html file, to methods isbn parameter
	public ModelAndView takeBook(@RequestParam(value = "isbn") String isbn){
		//get logged in user 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	    //get logged in user username
		String name = auth.getName(); 
	    //get logged in user data
		Users user = userService.findByUsername(name); 
	    // redirects to givetakebook view
	    ModelAndView mav = new ModelAndView("redirect:/givetakebook"); 
	    //checks if user user exists
	    if(user == null){ 
	    	//prints out messege in console if user isn't initialized
	    	System.out.println("Error: user is null"); 
	    	mav.setViewName("redirect:/givetakebook?user");
	    	return mav;
	    }
	    //code for later controller update 
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
	// sets /takebook path. Called when using POST method
	@RequestMapping(value="/takebook", method = RequestMethod.POST) 
	// binds values isbn, taken from html file, to methods isbn parameter
	public String giveBookByISBN(@RequestParam(value = "isbn") String isbn){ 
		//get logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	    //get logged in username
		String name = auth.getName();
	    //get logged in user data
	    Users user = userService.findByUsername(name); 
	    //check if user exists
	    if(user == null){ 
	    	//prints out messege in console if user isn't initialized
	    	System.out.println("Error: user is null");  
	    	return "redirect:/givetakebook";
	    }
	    //code for later controller update
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
		return "givetakebook"; //TODO show message if there isn't book with given isbn number
	}
}