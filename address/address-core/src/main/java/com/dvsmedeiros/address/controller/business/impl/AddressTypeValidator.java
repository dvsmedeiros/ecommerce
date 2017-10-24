package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;

@Component
public class AddressTypeValidator implements IStrategy<Address>{

	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {
		
		if(aEntity != null && aEntity.getType() != null) {
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Tipo de Endereço inexistente ou inválido");
	}

}
