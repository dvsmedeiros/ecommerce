package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class AddItemToCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;

	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		if( aCase.isSuspendExecution() ){
			aCase.getResult().setMessage("Não foi possível adicionar o Produto: " + aEntity.getId() + " ao carrinho!");
			return;
		}
		
		cart.addItem(aEntity);
		
	}

}
