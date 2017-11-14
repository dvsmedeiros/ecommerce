package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.shopcart.domain.Cart;

@Component
public class AddCupomToCart implements IStrategy<Cupom> {

	@Autowired
	private Cart cart;


	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		
		Cupom coupon = aCase.getContext().getAttribute("coupon");
		if(coupon != null) {
			cart.addCupom(coupon);			
		}
	}
}
