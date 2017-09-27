package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class RemoveAllItemOfCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		CartItem target = aCase.getContext().getAttribute("item");
		
		if(target == null){
			aCase.suspendExecution();
			aCase.getResult().setMessage("Produto: " + aEntity.getProduct().getDescription() + " não está no carrinho!");
		}
		
		cart.removeItems(target);
	}

}
