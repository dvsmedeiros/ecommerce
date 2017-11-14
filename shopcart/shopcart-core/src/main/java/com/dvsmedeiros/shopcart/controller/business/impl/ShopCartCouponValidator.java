package com.dvsmedeiros.shopcart.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.shopcart.domain.Cart;

@Component
public class ShopCartCouponValidator implements IStrategy<Cupom> {

	@Autowired
	@Qualifier("navigator")
	private INavigator<Cupom> navigator;

	@Autowired
	private Cart cart;

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {

		BusinessCase<Cupom> bCase = new BusinessCaseBuilder<Cupom>().withName("COUPON_VALIDATOR").build();
		User user = aCase.getContext().getAttribute("logged");
		bCase.getContext().setAttribute("logged", user);
		bCase.getContext().setAttribute("coupons", cart.getCupons());
		bCase.getContext().setAttribute("subTotal", cart.getSubTotal());
		bCase.getContext().setAttribute("totalCoupons", cart.getTotalCupons());
		bCase.getContext().setAttribute("isPresent", true);
		
		navigator.run(aEntity, bCase);
		if (bCase.isSuspendExecution() || bCase.getResult().hasError()) {
			aCase.suspendExecution(bCase.getResult().getMessage());
			return;
		}
		
		Cupom coupon = bCase.getContext().getAttribute("coupon");
		aCase.getContext().setAttribute("coupon", coupon);
	}
}
