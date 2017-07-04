package com.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bootcamp.repositories.BooksRepository;

@Controller
public class SelectedBookController {

	@Autowired
	BooksRepository bookrepository;
	
	@RequestMapping(value={"/selectedBook"}, method = RequestMethod.GET)
	public ModelAndView selectedBook(@RequestParam(value="title")String title){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("book",bookrepository.findFirstByTitleIgnoreCase(title));
		modelAndView.setViewName("selectedBook"); //demo or JAVA
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
