package com.dvsmedeiros.stock.controller.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.bce.domain.Result;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public class FindStockZero extends ApplicationEntity implements IStrategy<Filter<Stock>> {

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Stock> dao;
	
	@Override
	public void process(Filter<Stock> aEntity, INavigationCase<Filter<Stock>> aCase) {
		
		List<Stock> stocks = dao.findAll(Stock.class);
		List<Stock> stockZero = new ArrayList<>();
		if(stocks != null && !stocks.isEmpty()) {
			
			for (Stock stock : stocks) {
				if(stock.getCurrent().doubleValue() <= 0 && stock.getProduct().getActive()) {
					stockZero.add(stock);
				}
			}
		}
		aCase.getResult().addEntity(Result.RESULTS_KEY, stockZero);
		
	}

}
