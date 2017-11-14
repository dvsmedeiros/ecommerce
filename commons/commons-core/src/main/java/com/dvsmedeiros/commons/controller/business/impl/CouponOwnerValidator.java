package com.dvsmedeiros.commons.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;
import com.dvsmedeiros.commons.domain.User;

@Component
public class CouponOwnerValidator implements IStrategy<Cupom> {

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		
		Cupom coupon = aCase.getContext().getAttribute("coupon");
		User loggedUser = aCase.getContext().getAttribute("logged");
		
		if(coupon != null && coupon.getType().equals(CupomType.EXCHANGE) && coupon.getOwner() != null) {
			
			if(coupon.getOwner().getId() != loggedUser.getId()) {
				aCase.suspendExecution("Cupom de troca " + coupon.getCode() + " NÃO pertence a este usuário: " + loggedUser.getEmail());
			}
		}
	}
}
