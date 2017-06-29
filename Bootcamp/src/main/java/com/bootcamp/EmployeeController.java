package com.bootcamp;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	
	private String employee;
	
	@RequestMapping("/employee")
	public String employee(Map<String, Object> model) {
		model.put("employee", this.employee);
		return "employee";
		
	}
	
	
}