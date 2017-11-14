package com.dvsmedeiros.order.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;
import com.dvsmedeiros.order.controller.dao.impl.IOrderDAO;
import com.dvsmedeiros.order.domain.Order;

@Component
public class InactvationExchangeCoupons extends ApplicationEntity implements IStrategy<Order> {

	@Autowired
	private IOrderDAO dao;

	@Override
	public void process(Order aEntity, INavigationCase<Order> aCase) {

		if (aEntity != null && aEntity.getCupons() != null && !aEntity.getCupons().isEmpty()) {
			for (Cupom coupon : aEntity.getCupons()) {
				if (coupon.getType().equals(CupomType.EXCHANGE)) {
					dao.inactivate(Cupom.class, coupon.getCode());
				}
			}
		}
	}
}
