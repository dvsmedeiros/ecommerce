package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Individual;
import com.google.common.base.Strings;

@Component
public class LastNameValidator implements IStrategy<Individual> {

	@Override
	public void process(Individual aEntity, INavigationCase<Individual> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getLastName())) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Sobrenome inexistente ou inválido");

	}

}
