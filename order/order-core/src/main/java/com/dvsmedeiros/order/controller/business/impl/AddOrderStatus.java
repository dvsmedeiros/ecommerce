package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;

@Component
public class AddOrderStatus extends ApplicationEntity implements IStrategy<Order> {
	
	private static final String STATUS_ORDER_TARGET = "PROCESSING";
	
	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<StatusOrder> dao;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		if (!aCase.isSuspendExecution() && !aCase.getResult().hasError()) {
			StatusOrder status =  (StatusOrder) dao.find(StatusOrder.class, STATUS_ORDER_TARGET);
			if(status != null) {
				aEntity.setStatusOrder(status);
				return;
			}
			
			getLogger(this.getClass()).error("Status de pedido: " + STATUS_ORDER_TARGET + "não foi encontrado!");
			aCase.suspendExecution("Erro ao adicionar status no pedido!");
		}
	}
}
