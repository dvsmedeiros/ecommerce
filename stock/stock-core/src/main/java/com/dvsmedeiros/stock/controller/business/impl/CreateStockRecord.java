package com.dvsmedeiros.stock.controller.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.INavigator;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCase;
import com.dvsmedeiros.bce.core.controller.impl.BusinessCaseBuilder;
import com.dvsmedeiros.stock.domain.Stock;
import com.dvsmedeiros.stock.domain.StockRecord;

@Component
public class CreateStockRecord implements IStrategy<Stock> {
	
	@Autowired
	@Qualifier("navigator")
	private INavigator navigator;
	
	@Override
	public void process(Stock aEntity, INavigationCase<Stock> aCase) {

		if(aEntity != null && aEntity.getRecords() != null && !aEntity.getRecords().isEmpty() ) {
			
			BusinessCase<StockRecord> bCase = new BusinessCaseBuilder<StockRecord>().withName("CREATE_STOCK_RECORD").build();
			navigator.run(aEntity.getRecords().get(0), bCase);
			
			if (bCase.getResult().hasError()) {
				aCase.getResult().setMessage(bCase.getResult().getMessage());
			}
			return;
		}
		
		aCase.getResult().setMessage("Não foi possível criar registro de estoque");
	}

}
