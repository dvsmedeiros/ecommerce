package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.User;
import com.dvsmedeiros.order.domain.Order;
import com.dvsmedeiros.order.domain.StatusOrder;

@Component
public class OrderCouponValidator extends ApplicationEntity implements IStrategy<Order> {

	@Autowired
	@Qualifier("navigator")
	private INavigator<Cupom> navigator;

	@Autowired
	@Qualifier("genericDAO")
	private IDAO<StatusOrder> dao;

	public void process(Order aEntity, INavigationCase<Order> aCase) {

		BusinessCase<Cupom> bCase = new BusinessCaseBuilder<Cupom>().withName("COUPON_VALIDATOR").build();		
		bCase.getContext().setAttribute("logged", aEntity.getCustumer());
		bCase.getContext().setAttribute("coupons", aEntity.getCupons());
		bCase.getContext().setAttribute("subTotal", aEntity.getSubTotal());
		bCase.getContext().setAttribute("totalCoupons", aEntity.getTotalCupons());
		bCase.getContext().setAttribute("isPresent", false);

		for (Cupom coupon : aEntity.getCupons()) {
			navigator.run(coupon, bCase);
			if (bCase.isSuspendExecution() || bCase.getResult().hasError()) {
				aEntity.setStatusOrder((StatusOrder) dao.find(StatusOrder.class, StatusOrder.DISAPPROVED));
				aEntity.setDescription(bCase.getResult().getMessage());
			}
		}
	}
}
