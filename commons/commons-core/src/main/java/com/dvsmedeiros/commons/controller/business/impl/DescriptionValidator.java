package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.DomainEntity;
import com.dvsmedeiros.commons.domain.DomainSpecificEntity;

@Component
public class DescriptionValidator implements IStrategy<DomainEntity>{

	@Override
	public void process(DomainEntity aEntity, INavigationCase<DomainEntity> aCase) {
		if(aEntity != null && aEntity.getDescription() != null && !StringUtils.isEmpty(aEntity.getDescription())){
			return; 
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Descrição inexistente ou inválida!");
		
	}

}
