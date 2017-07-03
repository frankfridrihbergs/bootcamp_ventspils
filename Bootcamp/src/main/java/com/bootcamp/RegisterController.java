package com.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired UserRepository userRepository;

	@RequestMapping(value={"/register"}, method = RequestMethod.GET)
	public ModelAndView register(){
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
	}

}
