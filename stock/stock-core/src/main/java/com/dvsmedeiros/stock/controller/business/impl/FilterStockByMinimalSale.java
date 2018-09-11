package com.dvsmedeiros.stock.controller.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dvsmedeiros.bce.core.controller.INavigationCase;
import com.dvsmedeiros.bce.core.controller.business.IStrategy;
import com.dvsmedeiros.bce.core.dao.impl.ConfigurationDAO;
import com.dvsmedeiros.bce.core.dao.impl.GenericDAO;
import com.dvsmedeiros.bce.domain.ApplicationEntity;
import com.dvsmedeiros.bce.domain.Configuration;
import com.dvsmedeiros.bce.domain.Filter;
import com.dvsmedeiros.stock.domain.Stock;

@Component
public class FilterStockByMinimalSale extends ApplicationEntity implements IStrategy<Filter<Stock>> {

	@Autowired
	@Qualifier("genericDAO")
	private GenericDAO<Stock> dao;

	@Autowired
	@Qualifier("configurationDAO")
	private ConfigurationDAO configDAO;

	@Override
	public void process(Filter<Stock> aEntity, INavigationCase<Filter<Stock>> aCase) {

		List<Stock> stocks = aCase.getContext().getAttribute("stocks");
		
		
	}
	
	public Long getMinSale() {

		Configuration config = configDAO.findByCode(com.dvsmedeiros.commons.domain.Configuration.MIN_SALE_FOR_INACTIVATION);
		Long rate = config.getLongValue();

		return rate;
	}

}
