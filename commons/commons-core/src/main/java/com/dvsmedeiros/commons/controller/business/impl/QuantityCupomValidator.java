package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Configuration;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;

@Component
public class QuantityCupomValidator implements IStrategy<Cupom> {

	private static final long DEFAULT_QUANTITY = 1;
	@Autowired
	@Qualifier("genericDAO")
	private IDAO<Configuration> dao;
	
	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		
		Configuration configuration = (Configuration) dao.find(Configuration.class, Configuration.MAX_COUPON_PROMOTION);
		long limit = configuration.getLongValue() > 0 ? configuration.getLongValue() : DEFAULT_QUANTITY;
		
		List<Cupom> coupons = aCase.getContext().getAttribute("coupons");
		
		long sum = 0;
		for(Cupom c : coupons) {
			if(c.getType().equals(CupomType.PROMOTION)) {
				sum++;
			}
		}
		
		Cupom coupon = aCase.getContext().getAttribute("coupon");
		boolean isPresent = aCase.getContext().getAttribute("isPresent");
		if(coupon.getType().equals(CupomType.PROMOTION) && isPresent) ++sum;
		if(sum > limit && coupon != null) {
			aCase.suspendExecution("É permitido somente a utilização de " + limit + " cupom(ns) promocional(is)!");
		}
	}
}
