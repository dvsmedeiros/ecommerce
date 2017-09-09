package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingBoxLenghtValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_LENGHT_BOX = 16L;
	private final static Long MAX_VALUE_LENGHT_BOX = 105L;
	
	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getLength() != null
				&& aEntity.getEntity().getProduct().getPacking().getLength().longValueExact() < MIN_VALUE_LENGHT_BOX) {
			aEntity.getEntity().getProduct().getPacking().setLength(new BigDecimal(MIN_VALUE_LENGHT_BOX));
			return;
		}
		
		if (aEntity.getEntity().getProduct().getPacking().getLength() != null
				&& aEntity.getEntity().getProduct().getPacking().getLength().longValueExact() > MAX_VALUE_LENGHT_BOX) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("O comprimentonão pode ser maior que " + MAX_VALUE_LENGHT_BOX + " cm");
			return;
		}
	}

}
