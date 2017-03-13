package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class FindAndRemoveProductOfCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		Product target = aEntity.getProduct();
		
		for(CartItem c : cart.getCartItems()){
			
			if(c.getProduct().getId() == target.getId()){
				cart.removeItem(c);
				aCase.getResult().setMessage("Produto: " + target.getName() + " removido do carrinho!");
				return;
			}
		}
		aCase.suspendExecution();
		aCase.getResult().setMessage("Produto: " + target.getName() + " não está no carrinho!");
	}


}
