package com.bootcamp.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.models.BookList;
import com.bootcamp.models.Books;
import com.bootcamp.repositories.BookListRepository;
import com.bootcamp.repositories.BooksRepository;
import com.bootcamp.repositories.UserRepository;


@Controller    
public class DebtsController {
	
	
	@Autowired
	private BookListRepository bookListRepository;

	
	
	
	@RequestMapping(value="/debts", method = RequestMethod.GET)
		public ModelAndView debts(@RequestParam(value="username")String username){
		
			ModelAndView modelAndView = new ModelAndView();
			
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			long time = cal.getTimeInMillis();
			
			List<BookList> bookList = bookListRepository.findByUsernameAndExpiredBook(username, time);
			
			modelAndView.addObject("booklist", bookList);
			modelAndView.setViewName("debts");
			
			

			return modelAndView;
			
	}

}

