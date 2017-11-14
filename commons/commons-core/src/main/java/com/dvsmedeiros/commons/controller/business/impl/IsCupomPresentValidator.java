package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.commons.domain.Cupom;

@Component
public class IsCupomPresentValidator implements IStrategy<Cupom> {

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {

		boolean isPresent = aCase.getContext().getAttribute("isPresent");
		List<Cupom> coupons = aCase.getContext().getAttribute("coupons");
		if (coupons != null && !coupons.isEmpty() && isPresent) {
			for (Cupom coupon : coupons) {
				if (coupon != null && aEntity.getCode().equals(coupon.getCode())) {
					aCase.suspendExecution(
							"Cupom " + coupon.getCode().toUpperCase() + " não pode ser adicionado novamente");
					return;
				}
			}
		}
	}
}
