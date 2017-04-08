package com.dvsmedeiros.bce.controller.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.DomainEntity;
import com.dvsmedeiros.bce.domain.DomainSpecificEntity;

@Component
public class DescriptionValidator implements IStrategy<DomainEntity>{

	@Override
	public void process(DomainEntity aEntity, INavigationCase<DomainEntity> aCase) {
		if(aEntity != null && aEntity.getDescription() != null && !StringUtils.isEmpty(aEntity.getDescription())){
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Descri��o inexistente ou inv�lida!");
		
	}

}
