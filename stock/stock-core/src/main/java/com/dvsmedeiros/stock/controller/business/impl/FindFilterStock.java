package com.dvsmedeiros.stock.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.stock.controller.dao.IStockDAO;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public class FindFilterStock implements IStrategy<Filter<Stock>> {

	@Autowired
	private IStockDAO dao;

	@Override
	public void process(Filter<Stock> aEntity, INavigationCase<Filter<Stock>> aCase) {

		List<Stock> stocks = dao.filter(aEntity);
		aCase.getResult().addEntity("stocks", stocks);
	}

}
