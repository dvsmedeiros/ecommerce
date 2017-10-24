package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;
import com.google.common.base.Strings;

@Component
public class CPFValidator implements IStrategy<User> {

	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {

		if (aEntity != null && aEntity.getCpf() != null && !Strings.isNullOrEmpty(aEntity.getCpf().getNumber())) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("CPF inexistente ou inválido");

	}

}
