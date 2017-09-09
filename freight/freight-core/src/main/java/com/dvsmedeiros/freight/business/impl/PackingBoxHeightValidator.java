package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingBoxHeightValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_HEIGHT_BOX = 2L;
	private final static Long MAX_VALUE_HEIGHT_BOX = 105L;

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getHeight() != null
				&& aEntity.getEntity().getProduct().getPacking().getHeight().longValueExact() < MIN_VALUE_HEIGHT_BOX) {
			aEntity.getEntity().getProduct().getPacking().setHeight(new BigDecimal(MIN_VALUE_HEIGHT_BOX));
			return;
		}

		if (aEntity.getEntity().getProduct().getPacking().getHeight() != null
				&& aEntity.getEntity().getProduct().getPacking().getHeight().longValueExact() > MAX_VALUE_HEIGHT_BOX) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("A altura não pode ser maior que " + MAX_VALUE_HEIGHT_BOX + " cm");
			return;
		}
	}

}
