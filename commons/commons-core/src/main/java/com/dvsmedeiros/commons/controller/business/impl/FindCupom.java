package com.dvsmedeiros.commons.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.commons.controller.dao.ICupomDAO;
import com.dvsmedeiros.commons.domain.Cupom;
import com.google.common.base.Strings;

@Component
public class FindCupom implements IStrategy<Cupom> {

	@Autowired
	@Qualifier("cupomDAO")
	private ICupomDAO dao;

	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		
		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getCode())) {
			Filter<Cupom> filter = new Filter<>(Cupom.class);
			filter.getEntity().setCode(aEntity.getCode());
			filter.getEntity().setActive(Boolean.TRUE);
			List<Cupom> coupons = dao.filter(filter);
			
			if(coupons != null && !coupons.isEmpty() ) {
				Cupom coupon = coupons.get(0);
				aCase.getContext().setAttribute("coupon", coupon);
				return;
			}
		}
		aCase.suspendExecution("Cupom " + aEntity.getCode() == null ? "" : aEntity.getCode() +  " inexistente ou inválido");
	}

}
