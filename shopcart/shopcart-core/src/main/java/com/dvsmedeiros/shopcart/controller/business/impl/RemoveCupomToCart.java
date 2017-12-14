package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.shopcart.domain.Cart;
import com.google.common.base.Strings;

@Component
public class RemoveCupomToCart implements IStrategy<Cupom> {

	@Autowired
	private Cart cart;
	
	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getCode())) {
			if (cart != null && !cart.getCupons().isEmpty()) {
				for (Cupom c : cart.getCupons()) {
					if (c.getCode().equals(aEntity.getCode())) {
						cart.removeCupom(c);
						return;
					}
				}
			}
		}
	}
}
