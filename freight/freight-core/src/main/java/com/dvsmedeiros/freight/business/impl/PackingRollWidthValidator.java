package com.dvsmedeiros.freight.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.controller.INavigationCase;
import com.dvsmedeiros.bce.controller.business.IStrategy;
import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingRollWidthValidator implements IStrategy<FreightFilter> {

	private final static Long WIDTH_ROLL = 0L;
	
	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {
		
		if (aEntity.getEntity().getProduct().getPacking().getWidth() != null
				&& aEntity.getEntity().getProduct().getPacking().getWidth().longValueExact() != WIDTH_ROLL) {
				aCase.suspendExecution();
				aCase.getResult().setMessage("O Pacote do Tipo Rolo/Prisma não deve conter valor para a largura");
			return;
		}
	}

}
