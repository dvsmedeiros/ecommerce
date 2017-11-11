package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Cupom;

@Component
public class AddCupomValue extends ApplicationEntity implements IStrategy<Cupom> {
	
	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		Double value = aCase.getContext().getAttribute("value");
		if(value != null && value > 0) {
			aEntity.setValue(value);
			return;
		}
		aCase.suspendExecution("Valor do cupom inexiste ou inválido!");
	}
}
