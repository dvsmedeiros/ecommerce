package com.dvsmedeiros.freight.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingValidator implements IStrategy<FreightFilter> {

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {
		
		if(aEntity != null && aEntity.getEntity() != null && aEntity.getEntity().getProduct() != null && aEntity.getEntity().getProduct().getPacking() != null){
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Pacote Inexistente ou inválido!");
	}

}
