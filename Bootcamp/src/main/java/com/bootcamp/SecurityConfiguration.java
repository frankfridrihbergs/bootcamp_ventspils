package com.bootcamp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthSuccessHandler successHandler;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.role-query}")
	private String roleQuery;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(roleQuery)
				.dataSource(dataSource);
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers("/employeeIndex", "/checkout", "/gibetakebook", "/manage", "/register").hasAuthority("employee")
			.antMatchers("/userIndex", "/availableBooks", "/history", "/orderBook", "/selectedBook", "/debts").hasAuthority("reader")
			.antMatchers("/userIndex").hasAuthority("reader").anyRequest()
			.authenticated().and().csrf().disable().formLogin()
			.loginPage("/login").failureUrl("/login?error=true")
			.successHandler(successHandler)

			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").and().exceptionHandling()
			.accessDeniedPage("/access-denied");
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.debug(true);
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/vendor/**" , "/less/**", "/css/**" , "/js/**", "/img/**");
	       
	}

}