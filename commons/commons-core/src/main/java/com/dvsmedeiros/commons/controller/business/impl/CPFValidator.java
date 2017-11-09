package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Individual;
import com.google.common.base.Strings;

@Component
public class CPFValidator implements IStrategy<Individual> {

	@Override
	public void process(Individual aEntity, INavigationCase<Individual> aCase) {

		if (aEntity != null && aEntity.getCpf() != null && !Strings.isNullOrEmpty(aEntity.getCpf().getNumber())) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("CPF inexistente ou inválido");

	}

}
