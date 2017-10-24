package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.dao.impl.UserDAO;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class EmailValidator implements IStrategy<User> {

	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getEmail())) {
			UserDetails user = dao.loadUserByUsername(aEntity.getEmail());
			if( user != null) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("Email já cadastrado");
				return;
			}
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Email inexistente ou inválido");

	}

}
