package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.shopcart.domain.Cart;

@Component
public class CleanShopCart implements IStrategy<Order> {

	@Autowired
	private Cart cart;

	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {

		cart.cleanCart();
	}
}
