package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingRollDiameterValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_DIAMETER_ROLL = 5L;
	private final static Long MAX_VALUE_DIAMETER_ROLL = 91L;
	
	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getDiameter() != null
				&& aEntity.getEntity().getProduct().getPacking().getDiameter().longValueExact() < MIN_VALUE_DIAMETER_ROLL) {
			aEntity.getEntity().getProduct().getPacking().setDiameter(new BigDecimal(MIN_VALUE_DIAMETER_ROLL));
			return;
		}
		
		if (aEntity.getEntity().getProduct().getPacking().getDiameter() != null
				&& aEntity.getEntity().getProduct().getPacking().getDiameter().longValueExact() > MAX_VALUE_DIAMETER_ROLL) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("O diametro não pode ser maior que " + MAX_VALUE_DIAMETER_ROLL + " cm");
			return;
		}
	}

}
