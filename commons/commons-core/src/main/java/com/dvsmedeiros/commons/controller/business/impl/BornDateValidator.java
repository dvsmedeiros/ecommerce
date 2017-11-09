package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Individual;

@Component
public class BornDateValidator implements IStrategy<Individual> {

	@Override
	public void process(Individual aEntity, INavigationCase<Individual> aCase) {

		if (aEntity != null && aEntity.getBornDate() != null) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Data de Nascimento inexistente ou inválida");

	}

}
