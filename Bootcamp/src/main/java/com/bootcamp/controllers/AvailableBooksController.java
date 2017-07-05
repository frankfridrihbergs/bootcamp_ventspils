package com.bootcamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.repositories.BooksRepository;

@Controller
public class AvailableBooksController {
	
	@Autowired
	private BooksRepository bookRepository;
	
	@RequestMapping(value="/availableBooks", method = RequestMethod.GET)
	public String givetakeBook(Model model){
		model.addAttribute("books", bookRepository.findAll());
			
		return "availableBooks";
	}
	
	@RequestMapping(value="/availableBooks", method = RequestMethod.POST) // WILL SHOW THE BOOKS YOU SEARCHED FOR IN ORDERBOOK
	public String showSeachedBooks(Model model,@RequestParam(value="title", required = false) String title,@RequestParam(value="author",required = false) String author,
			@RequestParam(value="rating",required = false) Float rating,@RequestParam(value ="isbn",required =  false) String isbn){
		model.addAttribute("books", bookRepository.getBooksByInfo(title, author, rating, isbn));
			
		return "availableBooks";
	}
}