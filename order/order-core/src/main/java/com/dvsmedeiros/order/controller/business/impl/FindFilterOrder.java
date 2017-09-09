package com.dvsmedeiros.order.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.order.controller.dao.OrderDAO;
import com.dvsmedeiros.order.domain.Order;

@Component
public class FindFilterOrder implements IStrategy<Filter<Order>> {
	
	@Autowired
	private OrderDAO dao;
	
	@Override
	public void process(Filter<Order> aFilter, INavigationCase<Filter<Order>> aCase) {
		
		List<Order> orders = dao.filter(aFilter);
		aCase.getResult().addEntity("orders", orders);
		
	}

}
