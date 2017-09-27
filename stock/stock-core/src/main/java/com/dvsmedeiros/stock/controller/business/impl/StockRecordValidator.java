package com.dvsmedeiros.stock.controller.business.impl;

import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.stock.domain.StockRecord;

@Component
public class StockRecordValidator extends ApplicationEntity implements IStrategy<StockRecord> {
	
	@Override
	public void process(StockRecord aEntity, INavigationCase<StockRecord> aCase) {
		
		if(aEntity != null) {
			return;
		}
		
		aCase.suspendExecution();
		aCase.getResult().setMessage("Registro de Estoque inexistente ou inválido!");
	}

}
