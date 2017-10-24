package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;

@Component
public class GenderValidator implements IStrategy<User> {

	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && aEntity.getGender() != null) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Genero inexistente ou inválido");

	}

}
