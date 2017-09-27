package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Reason;
import com.google.common.base.Strings;

@Component
public class ReasonCodeValidator extends ApplicationEntity implements IStrategy<Reason>{

	@Override
	public void process(Reason aEntity, INavigationCase<Reason> aCase) {
		
		if(aEntity != null && !Strings.isNullOrEmpty(aEntity.getCode())) {
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Código inxistente ou inválido");
	}

}
