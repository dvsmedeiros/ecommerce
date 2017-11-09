package com.dvsmedeiros.commons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.commons.service.domain.ChangePassword;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.google.common.base.Strings;

@Controller
@RequestMapping("${bce.app.context}/client")
public class ClientController extends CommonsController<Client> {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<User> appFacade;
	
	public ClientController() {
		super(Client.class);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> getLogged() {
		return new ResponseEntity<>(getLoggedClient(), HttpStatus.OK);
	}
	
}
