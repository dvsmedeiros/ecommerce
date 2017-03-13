package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.commons.controller.INavigationCase;
import com.dvsmedeiros.commons.controller.business.IStrategy;
import com.dvsmedeiros.shopcart.domain.CartItem;

@Component
public class IdProductValidator implements IStrategy<CartItem>{

	@Override
	public void process(CartItem aEntity, INavigationCase<CartItem> aCase) {
		
		if(aEntity != null && aEntity.getProduct() != null && aEntity.getProduct().getId() > 0){
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("ID do produto inexistente ou inválido");
	}

}
