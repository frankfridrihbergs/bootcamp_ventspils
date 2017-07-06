package com.bootcamp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	
	@Autowired UserRepository userRepository;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("register");
    }
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String displayLogin(Model model) { 
	    model.addAttribute("user", new Users()); 
	    return "register"; 
	}
	
	@PostMapping("/register")
    public String checkPersonInfo(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        return "register";
    }
	
	
	

	/*@RequestMapping(value={"/register"}, method = RequestMethod.GET)
	public ModelAndView register(Users user){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		
		return modelAndView;
	}
	
	@GetMapping(path = "/save")
	public ModelAndView addNewUser(@RequestParam String name, @RequestParam String password,
										   @RequestParam String username) {
		
		ModelAndView mav = new ModelAndView();
		
		
		Users user = new Users();
		user.setName(name);
		user.setPassword(password);
		user.setUsername(username);
		user.setSurname("Test1");
		user.setRole("reader");
		
		userRepository.save(user);
		
		mav.addObject("UserSaved", "User has been saved!");
		mav.setViewName("register");
		
		return mav;
	}*/

}
