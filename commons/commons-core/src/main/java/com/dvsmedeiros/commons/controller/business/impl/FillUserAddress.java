package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.User;

@Component
public class FillUserAddress implements IStrategy<User>{

	@Override
	public void process(User aEntity, INavigationCase<User> aCase) {
		
		if(aEntity != null && aEntity.getAddresses() != null && !aEntity.getAddresses().isEmpty() ) {
			
			Address address = aEntity.getAddresses().get(0);
			address.setBilling(Boolean.TRUE);
			address.setDelivery(Boolean.TRUE);
			address.setHome(Boolean.TRUE);
			
		}
	}
}
