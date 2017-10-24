package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.commons.controller.dao.IUserDAO;
import com.dvsmedeiros.commons.domain.User;

@Component
public class FindFilterUser extends ApplicationEntity implements IStrategy<Filter<User>>{
	
	@Autowired
	@Qualifier("userDAO")
	private IUserDAO dao;

	@Override
	public void process(Filter<User> aEntity, INavigationCase<Filter<User>> aCase) {
		
		List<User> users = dao.filter(aEntity);
		aCase.getResult().addEntity(Result.RESULTS_KEY, users);
		
	}
	

}
