package com.dvsmedeiros.address.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.address.domain.Address;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
@Component
public class CityValidator implements IStrategy<Address> {

	@Override
	public void process(Address aEntity, INavigationCase<Address> aCase) {

		if (aEntity != null && aEntity.getNeighborhood() != null && aEntity.getNeighborhood() != null
				&& aEntity.getNeighborhood().getCity() != null
				&& aEntity.getNeighborhood().getCity().getDescription() != null
				&& !aEntity.getNeighborhood().getCity().getDescription().isEmpty()) {
			return;
		}

		aCase.suspendExecution();
		aCase.getResult().setMessage("Cidade inválido ou inexistente!");
	}

}
