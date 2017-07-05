package com.bootcamp.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter 
{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/checkout").setViewName("checkout");
		registry.addViewController("/debts").setViewName("debts");
		registry.addViewController("/employeeIndex").setViewName("employeeIndex");
		registry.addViewController("/history").setViewName("history");
		registry.addViewController("/manage").setViewName("manage");
		//registry.addViewController("/selectedBook").setViewName("selecteBook");
		registry.addViewController("/userIndex").setViewName("userIndex");
		
	}
}
