package com.dvsmedeiros.product.service;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dvsmedeiros.bce.conf.log.Log;
import com.dvsmedeiros.bce.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.controller.impl.Navigator;
import com.dvsmedeiros.commons.domain.User;

@Controller
public class AuthenticateController {

	private @Log Logger LOGGER;
	
	@Autowired
	private User loggedUser;
	
	@Autowired
	@Qualifier("navigator")
	private Navigator<User> navigator;

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseEntity<User> doLogin(@RequestBody User user) {

		BusinessCase<User> aCase = new BusinessCaseBuilder<User>().withName("DO_LOGIN").build();
		navigator.run(user, aCase);

		if (aCase.isSuspendExecution()) {
			LOGGER.error(aCase.getResult().getMessage());
			loggedUser = new User();
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody ResponseEntity<User> doLogout() {

		loggedUser = new User();
		loggedUser.setAuthenticated(false);
		
		return new ResponseEntity<>(loggedUser, HttpStatus.OK);
	}
	
}
