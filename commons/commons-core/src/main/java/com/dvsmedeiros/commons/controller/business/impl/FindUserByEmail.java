package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.dao.IUserDAO;
import com.dvsmedeiros.commons.domain.User;

@Component
public class FindUserByEmail implements IStrategy<User>{
	
	@Autowired
	private IUserDAO dao;
	
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {
		User user = dao.loadUserByUsername(aEntity.getEmail());
		aCase.getContext().setAttribute("user", user);
	}

}
