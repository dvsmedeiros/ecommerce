package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;

@Component
public class SendOrderToProcess extends ApplicationEntity implements IStrategy<Order> {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
        Order order = new Order();
        order.setId(aEntity.getId());
        order.setCode(StatusOrder.PAYMENT);
        jmsTemplate.convertAndSend("orders", order);
	}
}
