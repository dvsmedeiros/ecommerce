package com.dvsmedeiros.stock.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.stock.controller.dao.impl.StockDAO;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public class HasStock implements IStrategy<Filter<Stock>> {

	@Autowired
	@Qualifier("stockDAO")
	private StockDAO dao;

	@Override
	public void process(Filter<Stock> aEntity, INavigationCase<Filter<Stock>> aCase) {

		List<Stock> stocks = dao.filter(aEntity);
		if (stocks != null && !stocks.isEmpty()) {
			Stock stock = stocks.get(0);
			if (stock.getCurrent().doubleValue() > 0) {
				return;
			}
			aCase.suspendExecution();
			aCase.getResult().setMessage("Produto de código: " + stock.getProduct().getCode() + " não possui estoque!");
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setMessage("Estoqueinexistente ou inválido");
		return;
	}

}
