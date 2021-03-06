package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class AddItemToCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;

	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		CartItem item = aCase.getContext().getAttribute("item");
		if( item != null ){
			aEntity.getProduct().setCode(item.getProduct().getCode());
			cart.addItem(item);
			return;
		}
		cart.addItem(aEntity);
	}

}
