package com.dvsmedeiros.ecommerce.conf;

import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dvsmedeiros.supplier.controller.dao.impl.UserDAO;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(new String[]{"/products", "/products/**"}).hasRole("ADMIN")
		.antMatchers(new String[]{"/products/category", "/products/category/**"}).hasRole("ADMIN")
		.antMatchers(new String[]{"/supplier", "/supplier/**"}).hasRole("ADMIN")
		.antMatchers(new String[]{"/orders", "/orders/**"}).hasAnyRole(new String[]{"ADMIN", "USER"})
		.antMatchers(new String[]{"/home", "/home/**"}).hasAnyRole(new String[]{"ADMIN", "USER"})
		.antMatchers(HttpMethod.GET, "/products").permitAll()
		.antMatchers(HttpMethod.GET, "/products/**").permitAll()
		.antMatchers(HttpMethod.GET, "/products/category").permitAll()
		.antMatchers(HttpMethod.GET, "/supplier").permitAll()
		.antMatchers(HttpMethod.GET, "/supplier/**").permitAll()
		.antMatchers(HttpMethod.GET, "/products/category/**").permitAll()
		.antMatchers("/singup").permitAll()
		.antMatchers("/user").permitAll()
		.antMatchers("/cart").permitAll()
		.antMatchers("/cart/**").permitAll()
		.antMatchers("/freight").permitAll()
		.antMatchers("/freight/**").permitAll()
		.antMatchers("/checkout").permitAll()
		.antMatchers("/checkout/**").permitAll()
		.antMatchers("/").permitAll()
	    .anyRequest().authenticated()
	    .and().formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDao)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
