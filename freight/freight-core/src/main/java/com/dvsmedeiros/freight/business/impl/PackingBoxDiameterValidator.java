package com.dvsmedeiros.freight.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;import com.dvsmedeiros.freight.domain.FreightFilter;

@Component
public class PackingBoxDiameterValidator implements IStrategy<FreightFilter> {

	private final static Long DIAMETER_BOX = 0L;

	@Override
	public void process(FreightFilter aEntity, INavigationCase<FreightFilter> aCase) {

		if (aEntity.getEntity().getProduct().getPacking().getDiameter() != null
				&& aEntity.getEntity().getProduct().getPacking().getDiameter().longValueExact() != DIAMETER_BOX) {
			aCase.suspendExecution();
			aCase.getResult().setMessage("O Pacote do Tipo Pacote/Caixa não deve conter valor para o diametro");
			return;
		}
	}

}
