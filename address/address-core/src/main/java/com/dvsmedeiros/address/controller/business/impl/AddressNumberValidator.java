package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
@Component
public class AddressNumberValidator implements IStrategy<Address>{

	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {
		
		if(aEntity != null && aEntity.getaNumber() != null && ! aEntity.getaNumber().isEmpty()){
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Número inválido ou inexistente!");
	}

}
