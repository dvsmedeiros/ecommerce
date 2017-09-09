package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingBoxWidthValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_WIDTH_BOX = 11L;
	private final static Long MAX_VALUE_WIDTH_BOX = 105L;
	
	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getWidth() != null
				&& aEntity.getEntity().getProduct().getPacking().getWidth().longValueExact() < MIN_VALUE_WIDTH_BOX) {
			aEntity.getEntity().getProduct().getPacking().setWidth(new BigDecimal(MIN_VALUE_WIDTH_BOX));
			return;
		}
		
		if (aEntity.getEntity().getProduct().getPacking().getWidth() != null
				&& aEntity.getEntity().getProduct().getPacking().getWidth().longValueExact() > MAX_VALUE_WIDTH_BOX) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("A largura não pode ser maior que " + MAX_VALUE_WIDTH_BOX +  " cm");
			return;
		}
	}

}
