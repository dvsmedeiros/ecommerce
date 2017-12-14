package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.controller.ICouponFiller;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.order.domain.Order;

@Component
public class FillCouponExchange extends ApplicationEntity implements ICouponFiller {

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {

		Order order = aCase.getContext().getAttribute("order");
		if (order != null) {
			Cupom cupom = new Cupom();
			cupom.setOwner(order.getCustumer());
			cupom.setValue(order.getSubTotal().doubleValue());

			aCase.getContext().setAttribute("coupon", cupom);
		}
	}
}
