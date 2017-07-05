package com.bootcamp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserIndexController {
	
	@RequestMapping(value="/userIndex", method = RequestMethod.GET) //GET USER USERNAME AS VARIABLE
	public String userIndex(Model model){
	
		
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String username = auth.getName();
	
	model.addAttribute("username", username);
	
	return "userIndex";
	}
}
