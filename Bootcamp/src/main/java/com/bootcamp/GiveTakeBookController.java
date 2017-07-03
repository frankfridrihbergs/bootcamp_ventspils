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

@Controller
public class GiveTakeBookController {
	
	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private UsersService userService;
	
	@RequestMapping(value={"/givetakebook"}, method = RequestMethod.GET)
	public String givetakeBook(Model model){
		model.addAttribute("books", bookRepository.findAll());
		
		return "givetakebook";
	}
	
	@RequestMapping(value={"/givebook"}, method = RequestMethod.POST)
	public ModelAndView takeBook(@RequestParam(value = "isbn") String isbn){
		System.out.println("giving book with isbn: " + isbn);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    Users user = userService.findByUsername(name);
	    
	    ModelAndView mav = new ModelAndView("givetakebook");
	    mav.addObject("books", bookRepository.findAll());
	    mav.setViewName("givetakebook");
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
	public String giveBookByISBN(@RequestParam(value = "isbn") String isbn){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 		//get logged in username
	    Users user = userService.findByUsername(name);
	    if(user == null){ //check if user exists
	    	System.out.println("Error: user is null");
	    	return "givetakebook";
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
		return "givetakebook"; //TODO show message if there isn't book with given isbn number
	}
}
