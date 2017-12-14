package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Notification;
import com.dvsmedeiros.order.domain.Order;

@Component
public class GenerateExchangedNotification extends ApplicationEntity implements IStrategy<Order> {

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Notification> dao;

	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {

		Notification notification = new Notification();
		notification.setDescription("A troca do pedido: " + aEntity.getCode() + " foi a autorizada. Consulte seus cupons!");
		notification.setCode("TROCA DE PEDIDO");
		notification.setOwner(aEntity.getCustumer().getUser());
		notification.setReaded(Boolean.FALSE);

		dao.save(notification);
	}
}
