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
import com.dvsmedeiros.commons.controller.dao.IClientDAO;
import com.dvsmedeiros.commons.domain.Client;

@Component
public class FindFilterClient extends ApplicationEntity implements IStrategy<Filter<Client>>{
	
	@Autowired
	@Qualifier("clientDAO")
	private IClientDAO dao;

	@Override
	public void process(Filter<Client> aEntity, INavigationCase<Filter<Client>> aCase) {
		
		List<Client> clients = dao.filter(aEntity);
		aCase.getResult().addEntity(Result.RESULTS_KEY, clients);
		
	}
}
