package com.bootcamp.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bootcamp.models.Users;
import com.bootcamp.repositories.UserRepository;
import com.bootcamp.services.UsersService;

@Controller
public class RegisterController extends WebMvcConfigurerAdapter {
	
	@Autowired
	private UsersService userService;
	
	UserRepository userRepository;
	
	private Model model;
	
	public RegisterController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("register");
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String displayRegistration(Model model) { 
		this.model = model;
	    this.model.addAttribute("user", new Users());
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		String role = roles.toArray()[0].toString();
		System.out.println("logged user roles: " + role);
		
		this.model.addAttribute("role", role);
	    
	    return "register"; 
	}
	
	@PostMapping("/register")
    public String checkUserInfo(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, Model model) {
		System.out.println("User password: " + user.getName());
		System.out.println("User password: " + user.getPassword());
		System.out.println("User password: " + user.getRole()); 
		System.out.println("User password: " + user.getSurname());
		System.out.println("User password: " + user.getUsername());
		
		System.out.println(bindingResult.getAllErrors());
		
        if (bindingResult.hasErrors()) {
            return "register";
        }
 
		userRepository.save(user);
		
		model.addAttribute("UserSaved", "User has been saved!");

        return "register";
    }

}
