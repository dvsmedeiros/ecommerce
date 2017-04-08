package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.bce.controller.impl.Navigator;
import com.dvsmedeiros.product.domain.Product;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
@SuppressWarnings("rawtypes")
public class FindProductInCart implements IStrategy<CartItem>{
	
	@Autowired
	private Cart cart;
	@Autowired
	@Qualifier("navigator")
	private Navigator navigator;
	
	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		Product target = aEntity.getProduct();
		
		for(CartItem item : cart.getCartItems()){
			
			if(item.getProduct().getId() == target.getId()){
				aCase.getResult().setEntity(item);
				return;
			}
		}
	}
}
