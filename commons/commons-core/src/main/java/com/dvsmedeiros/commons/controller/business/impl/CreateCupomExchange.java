package com.dvsmedeiros.commons.controller.business.impl;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.commons.domain.Cupom;
import com.dvsmedeiros.commons.domain.CupomType;

@Component
public class CreateCupomExchange extends ApplicationEntity implements IStrategy<Cupom> {
	
	@Override
	public void process(Cupom aEntity, INavigationCase<Cupom> aCase) {
	
		aEntity.setType(CupomType.EXCHANGE);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 2);
		aEntity.setExpiryDate(calendar);
		
	}
}
