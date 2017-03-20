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
import com.dvsmedeiros.shopcart.domain.Cart;

@Component
public class CalculateFreightForCart implements IStrategy<Cart>{
	
	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;

	@Override
	public void process(Cart aEntity, INavigationCase<Cart> aCase) {
		
		BusinessCase<Freight> bCase = new BusinessCaseBuilder<Freight>().withName("CALCULATE_FREIGHT").build();
		navigator.run(aEntity.getFreight(), bCase);
		
		if(bCase.isSuspendExecution()){
			
			aCase.suspendExecution();
			aCase.getResult().setMessage(bCase.getResult().getMessage());
			return;
		}
			
		aEntity.setFreight(bCase.getResult().getEntity());
		aCase.getResult().setEntity(aEntity);
	}
	
}
