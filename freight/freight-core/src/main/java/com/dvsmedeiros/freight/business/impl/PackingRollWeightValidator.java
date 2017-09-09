package com.dvsmedeiros.freight.business.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingRollWeightValidator implements IStrategy<FreightFilter> {

	private final static Long MIN_VALUE_WEIGHT_ROLL = 0L;
	private final static Long MAX_VALUE_WEIGHT_ROLL = 30L;

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getWeight() != null
				&& aEntity.getEntity().getProduct().getPacking().getWeight().longValueExact() < MIN_VALUE_WEIGHT_ROLL) {
			aEntity.getEntity().getProduct().getPacking().setWeight(new BigDecimal(MIN_VALUE_WEIGHT_ROLL));
			return;
		}

		if (aEntity.getEntity().getProduct().getPacking().getWeight() != null
				&& aEntity.getEntity().getProduct().getPacking().getWeight().longValueExact() > MAX_VALUE_WEIGHT_ROLL) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("O peso não pode ser maior que " + MAX_VALUE_WEIGHT_ROLL + " kg");
			return;
		}
	}

}
