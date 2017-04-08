package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class RemoveItemOfCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		CartItem target = aCase.getResult().getEntity();
		
		if(target == null){
			aCase.suspendExecution();
			aCase.getResult().setMessage("Produto: " + aEntity.getProduct().getName() + " não está no carrinho!");
		}
		
		cart.removeItem(target);
	}

}
