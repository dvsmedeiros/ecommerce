package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Individual;
import com.google.common.base.Strings;

@Component
public class FirstNameValidator implements IStrategy<Individual> {

	@Override
	public void process(Individual aEntity, INavigationCase<Individual> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getFirstName())) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Nome inexistente ou inválido");

	}

}
