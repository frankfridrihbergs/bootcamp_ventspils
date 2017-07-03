package com.bootcamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AvailableBooksController {
	
	@Autowired
	private BooksRepository bookRepository;
	
	@RequestMapping(value="/availableBooks", method = RequestMethod.GET)
	public String givetakeBook(Model model){
		model.addAttribute("books", bookRepository.findAll());
			
		return "availableBooks";
	}
	
 //   public ModelAndView availablebooks() {
 //       ModelAndView availablebooks = new ModelAndView("availablebooks/list");
 //       availablebooks.addObject("availablebooks", bookRepository.findAll());
 //       return availablebooks;
 //   }
}