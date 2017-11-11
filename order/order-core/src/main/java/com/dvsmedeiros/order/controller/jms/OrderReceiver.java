package com.dvsmedeiros.order.controller.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.domain.Order;
import com.google.common.base.Strings;

@Component
public class OrderReceiver extends ApplicationEntity {
	
	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Order> appFacade;
	
	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Order> dao;
	
	@JmsListener(destination = "orders", containerFactory = "myFactory")
	public void receiveOrder(Order aMessage) {
		
		Order order = dao.find(aMessage.getId(), Order.class);
		if(order != null) {
			order.getStatusOrder().setCode(aMessage.getCode());
			appFacade.update(order, new BusinessCaseBuilder<Order>().withName("UPDATE_STATUS#".concat(aMessage.getCode())).build());
			return;
		}
		getLogger(this.getClass()).error("PEDIDO: " + aMessage.getCode() + " não foi encontrado!");
	}
}
