package com.bootcamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.models.Books;
import com.bootcamp.repositories.BooksRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class OrderBookController {
	
	@Autowired
	private BooksRepository br;
	
	@RequestMapping(value={"/orderbook"}, method = RequestMethod.GET) // if request method is GET then will do this
	public @ResponseBody ModelAndView searchBook() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orderBook");  // shows orderBook.html page
		
		return mav; 
	}
	
	@RequestMapping(value={"/orderbook"}, method = RequestMethod.POST)  // if request method is POST then will do this
	public @ResponseBody List<Books> orderBook(@RequestParam(value="title", required = false) String title,@RequestParam(value="author",required = false) String author,
			@RequestParam(value="rating",required = false) Float rating,@RequestParam(value ="isbn",required =  false) String isbn) {

		return br.getBooksByInfo(title, author, rating, isbn); // RETURNS BOOK list
	}

}
