package com.bootcamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
public class UserController {
	
	/*@GetMapping(path="/user")
	public @ResponseBody String find (@RequestParam String name) {
		Mys mys = new Mys();
		User n = null;
		
		//userRepository.save(n);
		if(n != null){
		return n.getSurname() + " |";
		}
		else
		{
			return "somthing went wrong";
		}
	}*/
	
}
