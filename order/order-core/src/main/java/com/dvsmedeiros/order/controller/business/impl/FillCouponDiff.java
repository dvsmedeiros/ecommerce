package com.dvsmedeiros.order.controller.business.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.IFacade;
import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.controller.ICouponFiller;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.order.domain.Order;

@Component
public class FillCouponDiff extends ApplicationEntity implements ICouponFiller {

	@Autowired
	@Qualifier("applicationFacade")
	private IFacade<Cupom> appFacade;

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {

		Order order = aCase.getContext().getAttribute("order");
		if( order != null) {			
			BigDecimal subTotal = order.getSubTotal();
			BigDecimal totalCupons = order.getTotalExchangeCupons();
			
			if (totalCupons.doubleValue() > subTotal.doubleValue()) {
				
				Cupom cupom = new Cupom();
				cupom.setOwner(order.getUser());
				cupom.setValue(totalCupons.subtract(subTotal).doubleValue());
				aCase.getContext().setAttribute("coupon", cupom);
			}
		}
	}
}
