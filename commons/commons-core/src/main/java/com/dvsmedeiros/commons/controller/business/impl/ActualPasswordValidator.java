package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class ActualPasswordValidator implements IStrategy<User> {
	
	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getActualPassword()) && !Strings.isNullOrEmpty(aEntity.getActualPassword())) {
			if(!aEntity.getPassword().equals(aEntity.getActualPassword())) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("Senha atual incorreta");
				return;
			}
		}
	}

}
