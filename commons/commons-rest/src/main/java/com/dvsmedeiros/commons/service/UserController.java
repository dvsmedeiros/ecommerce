package com.dvsmedeiros.commons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.User;

@Controller
@RequestMapping("${bce.app.context}/user")
public class UserController extends CommonsController<User> {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	public UserController() {
		super(User.class);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> getLogged() {
		return new ResponseEntity<>(getLoggedUser(), HttpStatus.OK);
	}
}
