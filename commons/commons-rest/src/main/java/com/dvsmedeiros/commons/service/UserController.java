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
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.commons.service.domain.ChangePassword;
import com.dvsmedeiros.rest.domain.ResponseMessage;
import com.google.common.base.Strings;

@Controller
@RequestMapping("${bce.app.context}/user")
public class UserController extends CommonsController<User> {
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<User> appFacade;
	
	public UserController() {
		super(User.class);
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> getLogged() {
		User loggedUser = getLoggedUser();
		if(loggedUser != null) {
			return new ResponseEntity<>(getLoggedUser(), HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "change/password", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword change) {
		
		User loggedUser = getLoggedUser();

		if (loggedUser != null && change != null && Strings.isNullOrEmpty(change.getActual())
				&& Strings.isNullOrEmpty(change.getNewPassword())
				&& Strings.isNullOrEmpty(change.getConfirmNewPassword())) {
			return new ResponseEntity<>("Parametros inválidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		loggedUser.setActualPassword(change.getActual());
		loggedUser.setNewPassword(change.getNewPassword());
		loggedUser.setConfirmPassword(change.getConfirmNewPassword());
			
		
		try {

			BusinessCase<User> bCase = new BusinessCaseBuilder<User>().withName("CHANGE_PASSWORD").build();
			Result result = appFacade.update(loggedUser, bCase);

			if (result.hasError()) {
				return new ResponseEntity<>(new ResponseMessage(Boolean.TRUE, result.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new ResponseMessage(Boolean.TRUE, "Erro ao alterar senha"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
