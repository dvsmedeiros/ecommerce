package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;

@Component
public class AddOrderStatus implements IStrategy<Order> {

	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		if (!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
			aEntity.setStatusOrder(StatusOrder.PROCESSING);
		}
	}
}
