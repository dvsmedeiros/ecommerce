package com.dvsmedeiros.commons.controller.business.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.IDAO;
import com.dvsmedeiros.bce.domain.Configuration;
import com.dvsmedeiros.commons.domain.Cupom;

@Component
public class TotalValueCupomValidator implements IStrategy<Cupom> {

	@Autowired
	@Qualifier("genericDAO")
	private IDAO<Configuration> dao;
	
	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
		
		BigDecimal subTotal =  aCase.getContext().getAttribute("subTotal");
		BigDecimal totalCupons =  aCase.getContext().getAttribute("totalCoupons");
		boolean isPresent =  aCase.getContext().getAttribute("isPresent");
		
		if (totalCupons.doubleValue() >= subTotal.doubleValue() && isPresent) {
			aCase.suspendExecution("Não é permitido a utilização de cupom que supere desnecessáriamente o valor da compra");
		}
	}
}
