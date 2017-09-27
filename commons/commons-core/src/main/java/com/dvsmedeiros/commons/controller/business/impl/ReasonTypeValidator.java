package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Reason;

@Component
public class ReasonTypeValidator extends ApplicationEntity implements IStrategy<Reason>{

	@Override
	public void process(Reason aEntity, INavigationCase<Reason> aCase) {
		
		if(aEntity != null && aEntity.getType() != null) {
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Tipo inexistente ou inválido");
	}

}
