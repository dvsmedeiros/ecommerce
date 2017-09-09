package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
@Component
public class StateValidator implements IStrategy<Address> {

	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {

		if (aEntity != null && aEntity.getNeighborhood() != null && aEntity.getNeighborhood() != null
				&& aEntity.getNeighborhood().getCity() != null
				&& !aEntity.getNeighborhood().getCity().getDescription().isEmpty()
				&& 	aEntity.getNeighborhood().getCity().getState() != null
				&& !aEntity.getNeighborhood().getCity().getState().getCode().isEmpty()) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Estado inválido ou inexistente!");
	}

}
