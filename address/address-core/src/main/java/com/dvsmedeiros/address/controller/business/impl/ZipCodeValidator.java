package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;

@Component
public class ZipCodeValidator implements IStrategy<Address>{

	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {
		
		if(aEntity != null && aEntity.getStreet() != null && ! aEntity.getStreet().isEmpty()){
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Rua inválida ou inexistente!");
	}

}
