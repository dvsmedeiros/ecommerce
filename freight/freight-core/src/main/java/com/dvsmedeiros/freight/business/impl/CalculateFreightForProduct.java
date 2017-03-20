package com.dvsmedeiros.freight.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.INavigator;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.commons.controller.impl.BusinessCase;
import com.dvsmedeiros.commons.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.freight.domain.Freight;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;

@Component
public class CalculateFreightForProduct implements IStrategy<Freight>{
	
	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;

	@Override
	public void process(Freight aEntity, INavigationCase<Freight> aCase) {
		
		BusinessCase<Freight> bCase = new BusinessCaseBuilder<Freight>().withName("CALCULATE_FREIGHT").build();
		navigator.run(aEntity, bCase);
		
		if(bCase.isSuspendExecution()){
			
			aCase.suspendExecution();
			aCase.getResult().setMessage(bCase.getResult().getMessage());
			return;
		}
			
		aCase.getResult().setEntity(bCase.getResult().getEntity());
	}
	
}
