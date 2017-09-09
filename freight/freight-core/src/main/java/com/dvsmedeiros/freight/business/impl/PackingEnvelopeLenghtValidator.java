package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingEnvelopeLenghtValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_LENGHT_ENVELOPE = 16L;
	private final static Long MAX_VALUE_LENGHT_ENVELOPE = 60L;
	
	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getLength() != null
				&& aEntity.getEntity().getProduct().getPacking().getLength().longValueExact() < MIN_VALUE_LENGHT_ENVELOPE) {
			aEntity.getEntity().getProduct().getPacking().setLength(new BigDecimal(MIN_VALUE_LENGHT_ENVELOPE));
			return;
		}
		
		if (aEntity.getEntity().getProduct().getPacking().getLength() != null
				&& aEntity.getEntity().getProduct().getPacking().getLength().longValueExact() > MAX_VALUE_LENGHT_ENVELOPE) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("O comprimentonão pode ser maior que " + MAX_VALUE_LENGHT_ENVELOPE + " cm");
			return;
		}
	}

}
