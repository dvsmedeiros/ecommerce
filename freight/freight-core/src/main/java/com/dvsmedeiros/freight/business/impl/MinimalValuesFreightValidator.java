package com.dvsmedeiros.freight.business.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.freight.domain.FreightFilter;
import com.dvsmedeiros.product.domain.PackingType;

@Component
public class MinimalValuesFreightValidator implements IStrategy<FreightFilter> {

	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;

	@Autowired
	@Qualifier("MIN_VALUES_PACKING")
	private Map<PackingType, String> minValues;

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		String bCaseName = minValues.get(aEntity.getEntity().getProduct().getPacking().getType());

		BusinessCase<FreightFilter> bCase = new BusinessCaseBuilder<FreightFilter>().withName(bCaseName).build();
		navigator.run(aEntity, bCase);

		if (bCase.isSuspendExecution()) {
			aCase.suspendExecution();
			aCase.getResult().setMessage(bCase.getResult().getMessage());
			return;
		}

	}

}
