package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.order.controller.dao.impl.IOrderDAO;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;
import com.google.common.base.Strings;

@Component
public class UpdateStatusOrder extends ApplicationEntity implements IStrategy<Order> {
	
	@Autowired
	private IOrderDAO dao;
	
	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<StatusOrder> genericDao;
	
	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {
		
		if (aEntity != null && aEntity.getStatusOrder() != null
				&& !Strings.isNullOrEmpty(aEntity.getStatusOrder().getCode())) {
			
			StatusOrder status = (StatusOrder) genericDao.find(StatusOrder.class, aEntity.getStatusOrder().getCode());
			if(status != null) {
				aEntity.setStatusOrder(status);				
				dao.updateStatus(aEntity);
				return;
			}
			aCase.suspendExecution("Status: " + aEntity.getCode() + " não foi encontrado");
			return;
		}
	}
}
