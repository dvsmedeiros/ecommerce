package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Component
public class ActiveValidator implements IStrategy<DomainSpecificEntity>{

	@Override
	public void process(DomainSpecificEntity aEntity, INavigationCase<DomainSpecificEntity> aCase) {
		if(aEntity != null && aEntity.getActive() != null ){
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Status inexistente ou inválido!");
		
	}

}
