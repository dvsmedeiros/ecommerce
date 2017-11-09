package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Individual;

@Component
public class UserAddressValidator implements IStrategy<Individual>{

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Override
	public void process(Individual aEntity, INavigationCase<Individual> aCase) {
		
		BusinessCase<Address> bCase = new BusinessCaseBuilder<Address>().withName("ADDRESS_VALIDATOR").build();
		
		if(aEntity != null && aEntity.getAddresses() != null && !aEntity.getAddresses().isEmpty()) {
			
			navigator.run(aEntity.getAddresses().get(0), bCase );
			if(!bCase.isSuspendExecution() && !bCase.getResult().hasError()) {
				return;
			}
			aCase.suspendExecution();
			aCase.getResult().setMessage(bCase.getResult().getMessage());
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Endereço Inexistente ou inválido");
		
	}

}
