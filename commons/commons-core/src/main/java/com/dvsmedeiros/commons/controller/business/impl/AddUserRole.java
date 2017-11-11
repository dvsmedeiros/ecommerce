package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Client;
import com.dvsmedeiros.commons.domain.User;

@Component
public class AddUserRole implements IStrategy<Client> {

	@Autowired
	@Qualifier("navigator")
	private INavigator<User> navigator;
	
	@Override
	public void process(Client aEntity, INavigationCase<Client> aCase) {
		
		BusinessCase<User> bCase = new BusinessCaseBuilder<User>().withName("USER_VALIDATOR").build();
		navigator.run(aEntity.getUser(), bCase);
		
		if(bCase.isSuspendExecution() || bCase.getResult().hasError()) {
			aCase.suspendExecution(bCase.getResult().getMessage());
		}
		aEntity.setEmail(aEntity.getUser().getEmail());
	}

}
