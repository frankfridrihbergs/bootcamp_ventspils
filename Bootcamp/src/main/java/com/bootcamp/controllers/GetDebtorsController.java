package com.bootcamp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootcamp.models.BookList;
import com.bootcamp.models.Users;
import com.bootcamp.repositories.BookListRepository;


@Controller
public class GetDebtorsController {

	@Autowired
	private BookListRepository blr;
	
	List<BookList> bl;

	@GetMapping(path = "/getDebtors")
	public String deleteBook(Model model) {
		
		Date date = new Date();
		String newstring = new SimpleDateFormat("yyyyMMdd").format(date); 
		      
		bl = blr.returnAllDebt(Integer.parseInt(newstring));  
		model.addAttribute("debtors", bl); 
		return "getDebtors";

	}
 
}
